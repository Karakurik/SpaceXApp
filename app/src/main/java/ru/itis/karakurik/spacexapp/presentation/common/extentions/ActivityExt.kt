package ru.itis.karakurik.spacexapp.presentation.common.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

internal fun AppCompatActivity.findController (id: Int) : NavController {
    return (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
}
