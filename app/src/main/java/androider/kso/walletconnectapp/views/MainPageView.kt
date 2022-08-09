package androider.kso.walletconnectapp

import androider.kso.walletconnectapp.ui.theme.Purple
import androider.kso.walletconnectapp.viewmodels.MainViewModel
import androider.kso.walletconnectapp.widgets.introductionText
import androider.kso.walletconnectapp.widgets.stepList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


private const val TAG: String = "MainPageView"

@Composable
fun MainPageView(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {


    Scaffold(
        topBar = {
            TopAppBar(title = {Text("WalletConnectApp", fontSize = 14.sp)}, backgroundColor = MaterialTheme.colors.primary)
        },
        content = {
            paddingValues -> ContentView(
                paddingValues = paddingValues,
                mainViewModel = mainViewModel,
                navHostController = navHostController
            )
        }
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentView(
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel,
    navHostController: NavHostController
){
    val context = LocalContext.current
    val walletAddress = mainViewModel.userWallet.collectAsState().value


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = .1F)),
            contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            introductionText()
            stepList(isWalletConnected = false) {
                mainViewModel.connectWallet(context)
            }
        }

        Text(text = walletAddress,
            style = MaterialTheme.typography.h6.copy(fontSize = 10.sp, color = Purple),
            textAlign = TextAlign.Center, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp))
    }

}






