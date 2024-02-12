package coinCapApp.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import coinCapApp.presentation.screens.CoinListScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(
            screen = CoinListScreen()
        )
    }
}