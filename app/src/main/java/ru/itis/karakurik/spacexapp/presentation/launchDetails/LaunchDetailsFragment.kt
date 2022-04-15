package ru.itis.karakurik.spacexapp.presentation.launchDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.karakurik.spacexapp.R
import ru.itis.karakurik.spacexapp.databinding.FragmentLaunchDetailsBinding
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.utils.DateHelper

@AndroidEntryPoint
class LaunchDetailsFragment : Fragment() {

    private var _binding: FragmentLaunchDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: LaunchDetailsFragmentArgs by navArgs()
    private val viewModel: LaunchDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        viewModel.onGetLaunch(args.launchId)
    }

    private fun initObservers() {
        with(viewModel) {
            launch.observe(viewLifecycleOwner) { result ->
                result.fold(
                    onSuccess = {
                        setLaunchData(it)
                    }, onFailure = {
                        Toast.makeText(
                            requireContext(),
                            "Не удалось получить информацию о полете",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }
    }

    private fun setLaunchData(launch: Launch) {
        with(binding) {
            tvHello.text = DateHelper.convertTimeStampToString(launch.dateUnix)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
