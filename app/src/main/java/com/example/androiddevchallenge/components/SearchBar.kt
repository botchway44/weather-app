package com.example.androiddevchallenge.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import java.util.*
import kotlin.reflect.KProperty

@Composable
fun SearchBar(
    modifier: Modifier
){
    Row(
       modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Image(
            painter = painterResource(R.drawable.ic_magnifying_glass),
            contentDescription = "description of the image",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(16.dp)

        )


        BasicTextField(
            value = "Search",
            onValueChange = {   },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(start = 17.dp)
                .align(Alignment.CenterVertically)
                .onFocusChanged { state ->

                },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Send
            ),
            maxLines = 1,
            cursorBrush = SolidColor(LocalContentColor.current),
            textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
        )

        Image(
            painter = painterResource(R.drawable.ic_edit),
            contentDescription = "description of the image",
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .height(15.dp)
        )
    }

}


private operator fun String.getValue(text: String, property: KProperty<*>): String {
        return text
}



@Composable
fun SearchBarLayout(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {

    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->


        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(constraints) }

        layout(
            width = constraints.maxWidth,
            height = constraints.minHeight

        ) {

            //Manually place items on screen
            placeables[0].place(0,0);
            placeables[1].place(10,0);
            placeables[2].place(constraints.maxWidth - 100,0);

//            Place items in their respective positions
//            placeables.forEachIndexed {index, placeable ->
//                placeable.place(
//                    x = 0,
//                    y = 0
//                )
//            }
        }
    }
}