package androider.kso.walletconnectapp.app

import androider.kso.walletconnectapp.views.MainPageView
import androider.kso.walletconnectapp.viewmodels.MainViewModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class NavPath(
    val route: String,
) {
    MainPage(route = "main_page"),
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavPath.MainPage.route

    ) {

        composable(
            NavPath.MainPage.route
        ) {
            val homePageViewModel: MainViewModel = hiltViewModel()

            MainPageView(
                navHostController = navHostController,
                mainViewModel = homePageViewModel,
            )
        }



    }

}

