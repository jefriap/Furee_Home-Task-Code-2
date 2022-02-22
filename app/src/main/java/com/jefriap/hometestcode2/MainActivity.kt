package com.jefriap.hometestcode2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefriap.hometestcode2.ui.theme.HomeTestCodeFuree2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeTestCodeFuree2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Layout()
                }
            }
        }
    }
}

@Composable
fun Layout() {
    var input by remember { mutableStateOf("") }
    val hasilnya = remember { mutableStateOf("0") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TextField(
            value = input,
            onValueChange = { newValue ->
                if (newValue.length <= 6) {
                    input = newValue
                }
            },
            placeholder = {
                Text(text = "Masukan angka disini")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        Button(
            onClick = {
                hasilnya.value = convert(input.toInt())
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(text = "Hasilnya")
        }
        
        Text(text = hasilnya.value)
    }
}

fun convert( angka: Int ): String {
    val angkaTerbilang = listOf(
        "",
        "Satu",
        "Dua",
        "Tiga",
        "Empat",
        "Lima",
        "Enam",
        "Tujuh",
        "Delapan",
        "Sembilan",
        "Sepuluh",
        "Sebelas"
    )
    var hasil: String = ""

    when(angka) {
        in 1..11 -> {
            hasil = angkaTerbilang[angka]
        }
        in 12..19 -> {
            hasil = convert(angka - 10) + " Belas"
        }
        in 20..99 -> {
            hasil = convert(angka / 10) + " Puluh " + convert(angka % 10)
        }
        in 100..199 -> {
            hasil = "Seratus " + convert(angka - 100)
        }
        in 200..999 -> {
            hasil = convert(angka / 100) + " Ratus " + convert(angka % 100)
        }
        in 1000..1999 -> {
            hasil = "Seribu " + convert(angka - 1000)
        }
        in 2000..999999 -> {
            hasil = convert(angka / 1000) + " Ribu " + convert(angka % 1000)
        }
    }

    return hasil
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeTestCodeFuree2Theme {
    }
}