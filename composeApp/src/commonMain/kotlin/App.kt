package coinCapApp.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import coinCapApp.presentation.coinCapApp.data.di.commonModule
import coinCapApp.presentation.coinCapApp.data.di.platformModule
import coinCapApp.presentation.screens.CoinListScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.compose.KoinApplication

@Composable
fun App() {
    MaterialTheme {
        Napier.base(DebugAntilog())
        KoinApplication(
            moduleList = { listOf(commonModule, platformModule()) },
            content = {
                Navigator(
                    screen = CoinListScreen()
                )
            })
    }
}