package coinCapApp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class CoinDetailScreen(
    private val coinItem: CoinItem,
    val onBack: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            val priceUsd = BigDecimal.fromDouble(coinItem.priceUsd)
            val formattedPrice = priceUsd.roundSignificand(DecimalMode(2, RoundingMode.CEILING)).toPlainString()

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier,
                    onClick = onBack,
                    content = { Icon(imageVector = Icons.Default.ArrowBack, tint = Color.White, contentDescription = null) },
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                    text = "${coinItem.name}(${coinItem.symbol})",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
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
    }
}