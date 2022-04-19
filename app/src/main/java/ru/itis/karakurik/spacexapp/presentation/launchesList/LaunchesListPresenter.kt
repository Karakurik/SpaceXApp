package ru.itis.karakurik.spacexapp.presentation.launchesList

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchesUseCase
import javax.inject.Inject

class LaunchesListPresenter @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : MvpPresenter<LaunchesListView>() {

    private var disposables = CompositeDisposable()

    fun onGetLaunchesList() {
        getLaunchesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showLaunchesListData(it)
            }, onError = { error ->
                viewState.consumerError(error)
            }).addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
