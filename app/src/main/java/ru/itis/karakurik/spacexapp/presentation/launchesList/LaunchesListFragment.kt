package ru.itis.karakurik.spacexapp.presentation.launchesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.karakurik.spacexapp.databinding.FragmentLaunchesListBinding
import ru.itis.karakurik.spacexapp.presentation.launchesList.recycler.LaunchesListRecyclerAdapter

@AndroidEntryPoint
class LaunchesListFragment : Fragment() {

    private var _binding: FragmentLaunchesListBinding? = null
    private val binding get() = _binding!!

    private var listRecyclerAdapter: LaunchesListRecyclerAdapter? = null

    private val viewModel: LaunchListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLaunchesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    private fun initObservers() {
        viewModel.launchesList.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = {
                    listRecyclerAdapter?.submitList(it)
                }, onFailure = {

                }
            )
        }
    }

    private fun initRecyclerView() {
        binding.rvLaunchesList.run {
            listRecyclerAdapter = LaunchesListRecyclerAdapter { id ->
                showDetailsFragment(id)
            }
            adapter = listRecyclerAdapter
        }
        viewModel.onGetLaunchesList()
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.let {
            it.setOnRefreshListener {
                viewModel.onGetLaunchesList()
                it.isRefreshing = false
            }
        }
    }

    private fun showDetailsFragment(launchId: String) {
        findNavController().navigate(
            LaunchesListFragmentDirections
                .actionLaunchesListFragmentToLaunchDetailsFragment(
                    launchId
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
