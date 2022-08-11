package androider.kso.walletconnectapp.views

import androider.kso.walletconnectapp.ui.theme.Blue700
import androider.kso.walletconnectapp.ui.theme.Yellow200
import androider.kso.walletconnectapp.viewmodels.MainViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    val isWalletConnected = mainViewModel.isWalletConnected.collectAsState().value

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            AnimatedVisibility(
                visible = isWalletConnected,
                enter = fadeIn(
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 250)
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){

                    Text(
                        text = "Wallet Address : ",
                        style = MaterialTheme.typography.h6.copy(fontSize = 14.sp, color = Color.Black),
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = walletAddress,
                        style = MaterialTheme.typography.h6.copy(fontSize = 13.sp, color = Blue700),
                        textAlign = TextAlign.Center,
                    )

                }
            }


            ConnectButton(isWalletConnected) {
                when(isWalletConnected){
                    false -> mainViewModel.connectWallet(context)
                    true -> mainViewModel.disconnectWallet()
                }

            }
        }



    }

}

@Composable
fun ConnectButton(isWalletConnected: Boolean, onConnectClick: () -> Unit) {
    val text = if (isWalletConnected) "Disconnect Wallet" else "Connect Wallet"
    Button(modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(15.dp))
        .fillMaxWidth()
        .height(44.dp),
        onClick = onConnectClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow200,
            contentColor = Color.Black)
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}





