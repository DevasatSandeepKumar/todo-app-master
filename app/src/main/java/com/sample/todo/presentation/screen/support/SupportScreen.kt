package com.sample.todo.presentation.screen.support

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sample.todo.presentation.navigation.MainNavGraph

@MainNavGraph
@Destination
@Composable
fun SupportScreen(
    navigator: DestinationsNavigator
) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> SupportPortraitScreen(onBackButtonClick = navigator::popBackStack)
        else -> SupportLandscapeScreen(onBackButtonClick = navigator::popBackStack)
    }
}