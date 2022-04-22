package ru.itis.karakurik.spacexapp.presentation.launchDetails

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip
import ru.itis.karakurik.spacexapp.domain.entity.Launch

@AddToEnd
interface LaunchDetailsView : MvpView {
    fun showLoading()

    fun hideLoading()

    @Skip
    fun consumerError(throwable: Throwable)

    fun showLaunchData(launch: Launch)
}
