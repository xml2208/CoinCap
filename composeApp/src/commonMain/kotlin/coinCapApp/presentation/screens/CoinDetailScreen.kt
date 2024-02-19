package coinCapApp.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import coinCapApp.data.models.CoinItem
import coinCapApp.data.remote.HttpRoutes
import coinCapApp.presentation.composables.CoinIconBlock
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay

class CoinDetailScreen(
    private val topCoins: List<CoinItem>,
    private val coinItem: CoinItem,
    val onBack: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val priceUsd = BigDecimal.fromDouble(coinItem.priceUsd)
                val formattedPrice = priceUsd.roundSignificand(DecimalMode(2, RoundingMode.CEILING)).toPlainString()
                AppBar()
                KamelImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(top = 25.dp, bottom = 10.dp).size(100.dp).clip(CircleShape),
                    resource = asyncPainterResource(data = HttpRoutes.GET_ICONS + coinItem.symbol.lowercase()),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "$${formattedPrice}",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            TopCoinsBlock(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp))
        }
    }

    @Composable
    private fun AppBar() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier,
                onClick = onBack,
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                },
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                text = "${coinItem.name}(${coinItem.symbol})",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }

    @Composable
    private fun TopCoinsBlock(modifier: Modifier) {
        val state = rememberLazyListState()
        var scrollDirection by remember { mutableStateOf(1) }

        LaunchedEffect(Unit) {
            val scrollableItems = topCoins.size - state.layoutInfo.visibleItemsInfo.size + 1
            val scrollWidth = scrollableItems * (COIN_ICONS_PADDING + COIN_ICON_WIDTH)
                delay(800L)
                while (true) {
                    state.animateScrollBy(
                        value = (scrollDirection * scrollWidth).toFloat(),
                        animationSpec = tween(
                            durationMillis = 4000,
                            easing  = LinearEasing
                        )
                    )
                    scrollDirection *= 1
                }
            }

        Column(modifier = modifier) {
            Text(
                text = "TOP COINS:",
                fontWeight = FontWeight.Bold,
            )
            LazyRow(state = state) {
                items(topCoins) {
                    CoinIconBlock(
                        symbol = it.symbol,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }

    companion object {
        private const val COIN_ICON_WIDTH = 40
        private const val COIN_ICONS_PADDING = 5
    }

}