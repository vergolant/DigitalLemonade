package com.example.digitallemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.digitallemonade.ui.theme.DigitalLemonadeTheme
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalLemonadeTheme {
                    DigitalLemonadeApp()
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun DigitalLemonadeApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Верхняя панель с заголовком
        LemonAppHeader()

        // Основное содержимое
        LemonTreeScreen(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Распределение пространства
        )
    }
}


@Composable


fun LemonAppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(red = 249, green = 228, blue = 76))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lemonade",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}



@Composable
fun LemonTreeScreen(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)

) {

    var state = remember { mutableStateOf(1) }
    var tapCount = remember { mutableStateOf((2..4).random()) }
    var taps = remember { mutableStateOf(0) }

    val imageResource = when (state.value) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val contentText = when (state.value) {
        1 -> R.string.Lemon_tree_text
        2 -> R.string.Lemon_squeeze_text
        3 -> R.string.Lemon_drink_text
        else -> R.string.Lemon_empty_text
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {
                if (state.value == 2) {
                    taps.value++
                    if (taps.value >= tapCount.value) {
                        state.value = 3
                        taps.value = 0
                        tapCount.value = (2..4).random()
                    }
                } else {
                    state.value = when (state.value) {
                        1 -> 2
                        3 -> 4
                        else -> 1
                    }
                }
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(red = 195, green = 236, blue = 210))
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageResource.toString()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(contentText),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp,
        )
    }
}