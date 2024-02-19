package coinCapApp.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import coinCapApp.presentation.coinCapApp.data.di.appModule
import coinCapApp.presentation.screens.CoinListScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    MaterialTheme {
        KoinApplication(
            moduleList = { listOf(appModule) },
            content = {
                Navigator(
                    screen = CoinListScreen()
                )
            })
    }
}