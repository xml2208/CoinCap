package coinCapApp.presentation.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchUi(
    modifier: Modifier,
    query: String,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier.indicatorLine(enabled = true, isError = false, interactionSource, colors),
        value = query,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = {
            TextFieldDefaults.TextFieldDecorationBox(
                value = query,
                innerTextField = it,
                enabled = true,
                placeholder = { Text("Search for a coin...") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                )
        }
    )
}

@Composable
private fun TextFieldDefaults.colors(): TextFieldColors = textFieldColors(
    textColor = Color.Black,
    backgroundColor = Color.Transparent,
    focusedIndicatorColor = Color.White,
    unfocusedIndicatorColor = Color.White,
    )