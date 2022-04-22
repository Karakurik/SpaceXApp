package ru.itis.karakurik.spacexapp.presentation.launchesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.itis.karakurik.spacexapp.databinding.FragmentLaunchesListBinding
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toInvisible
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toVisible
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toastLong
import ru.itis.karakurik.spacexapp.presentation.launchesList.recycler.LaunchesListRecyclerAdapter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class LaunchesListFragment : MvpAppCompatFragment(), LaunchesListView {

    private var _binding: FragmentLaunchesListBinding? = null
    private val binding get() = _binding!!

    private var listRecyclerAdapter: LaunchesListRecyclerAdapter? = null

    @Inject
    lateinit var presenterProvider: Provider<LaunchesListPresenter>

    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

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

        initSwipeRefreshLayout()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvLaunchesList.run {
            listRecyclerAdapter = LaunchesListRecyclerAdapter { id ->
                openLaunchDetailsScreen(id)
            }
            adapter = listRecyclerAdapter
        }
        presenter.onGetLaunchesList()
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.let {
            it.setOnRefreshListener {
                presenter.onGetLaunchesList()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {
        binding.progress.toVisible()
    }

    override fun hideLoading() {
        binding.progress.toInvisible()
    }

    override fun consumerError(throwable: Throwable) {
        requireContext().toastLong("Не удалось загрузить")
    }

    override fun showLaunchesListData(launchesList: List<Launch>) {
        listRecyclerAdapter?.submitList(launchesList)
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun openLaunchDetailsScreen(launchId: String) {
        findNavController().navigate(
            LaunchesListFragmentDirections.actionLaunchesListFragmentToLaunchDetailsFragment(
                launchId
            )
        )
    }
}
