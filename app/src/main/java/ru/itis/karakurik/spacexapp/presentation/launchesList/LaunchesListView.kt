package ru.itis.karakurik.spacexapp.presentation.launchesList

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip
import ru.itis.karakurik.spacexapp.domain.entity.Launch

@AddToEndSingle
interface LaunchesListView : MvpView {
    fun showLoading()

    fun hideLoading()

    @Skip
    fun consumerError(throwable: Throwable)

    fun showLaunchesListData(launchesList: List<Launch>)

    @OneExecution
    fun openLaunchDetailsScreen(launchId: String)
}
