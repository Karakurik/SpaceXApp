package ru.itis.karakurik.spacexapp.presentation.launchDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.itis.karakurik.spacexapp.R
import ru.itis.karakurik.spacexapp.databinding.FragmentLaunchDetailsBinding
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.utils.DateHelper
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toGone
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toVisible
import ru.itis.karakurik.spacexapp.presentation.common.extentions.toastLong
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class LaunchDetailsFragment : MvpAppCompatFragment(), LaunchDetailsView {

    private var _binding: FragmentLaunchDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: LaunchDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: Provider<LaunchDetailsPresenter>

    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onGetLaunch(args.launchId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {
        binding.progress.toVisible()
    }

    override fun hideLoading() {
        binding.progress.toGone()
    }

    override fun consumerError(throwable: Throwable) {
        requireContext().toastLong("Не удалось получить информацию о полете")
    }

    override fun showLaunchData(launch: Launch) {
        with(binding) {
            tvLaunchName.text = launch.name
            ivLaunchIcon.load(launch.largeImageUrl) {
                placeholder(R.drawable.launch_icon_large)
                crossfade(true)
            }
            tvLaunchDescription.text = launch.details
            ivLaunchIcon.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(launch.webCastUrl)
                    )
                )
            }
        }
    }
}
