package ru.itis.karakurik.spacexapp.presentation.launchesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.itis.karakurik.spacexapp.domain.entity.Launch
import ru.itis.karakurik.spacexapp.domain.usecases.GetLaunchesUseCase
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var _launchesList: MutableLiveData<Result<List<Launch>>> = MutableLiveData()
    val launchesList get() = _launchesList

    fun onGetLaunchesList() {
        getLaunchesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                // showLoading()
            }
            .doAfterSuccess {
                // hideLoading
            }
            .subscribeBy(onSuccess = {
                _launchesList.value = Result.success(it)
            }, onError = { error ->
                _launchesList.value = Result.failure(error)
            }).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
