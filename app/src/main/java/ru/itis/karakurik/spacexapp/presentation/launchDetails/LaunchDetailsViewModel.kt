package ru.itis.karakurik.spacexapp.presentation.launchDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchUseCase
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val getLaunchUseCase: GetLaunchUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var _launch: MutableLiveData<Result<Launch>> = MutableLiveData()
    val launch get() = _launch

    fun onGetLaunch(launchId: String) {
        getLaunchUseCase(launchId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // showLoading()
            }
            .doAfterSuccess {
                // hideLoading
            }
            .subscribeBy(onSuccess = {
                _launch.value = Result.success(it)
            }, onError = { error ->
                _launch.value = Result.failure(error)
            }).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
