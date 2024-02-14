package coinCapApp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coinCapApp.data.models.CoinItem
import coinCapApp.data.remote.HttpRoutes
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.integer.BigInteger
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CoinItem(
    coinItem: CoinItem,
    onClick: (CoinItem) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick(coinItem) }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KamelImage(
            modifier = Modifier.size(40.dp),
            resource = asyncPainterResource(HttpRoutes.GET_ICONS + coinItem.symbol.lowercase()),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(horizontal = 10.dp).weight(2f).fillMaxWidth()) {
            Text(
                text = coinItem.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = coinItem.symbol,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            val priceUsd = BigDecimal.fromDouble(coinItem.priceUsd)
            val formattedPrice = priceUsd.roundSignificand(DecimalMode(2, RoundingMode.CEILING)).toPlainString()
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$$formattedPrice",
                fontSize = 20.sp,
                textAlign = TextAlign.End,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                SetChangeUsdIcon(coinItem.changePercent24Hr.toString())
                Text(
                    text = coinItem.changePercent24Hr.toString().dropLast(14) + "%",
                    textAlign = TextAlign.End,
                )
            }
        }
    }
    Divider(modifier = Modifier.padding(horizontal = 20.dp))
}

@Composable
fun SetChangeUsdIcon(amount: String) {
    if (amount.startsWith('-')) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            tint = Color.Red,
            contentDescription = null
        )
    } else {
        Icon(
            modifier = Modifier,
            imageVector = Icons.Default.KeyboardArrowUp,
            tint = Color.Green,
            contentDescription = null
        )
    }
}