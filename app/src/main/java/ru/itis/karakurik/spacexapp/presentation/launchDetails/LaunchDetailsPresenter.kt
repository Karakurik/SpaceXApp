package ru.itis.karakurik.spacexapp.presentation.launchDetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchUseCase
import javax.inject.Inject

class LaunchDetailsPresenter @Inject constructor(
    private val getLaunchUseCase: GetLaunchUseCase
) : MvpPresenter<LaunchDetailsView>() {

    private val disposables = CompositeDisposable()

    fun onGetLaunch(launchId: String) {
        getLaunchUseCase(launchId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doFinally {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showLaunchData(it)
            }, onError = {
                viewState.consumerError(it)
            }).addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
