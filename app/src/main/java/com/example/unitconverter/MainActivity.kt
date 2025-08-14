package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.nio.file.WatchEvent
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                    UnitConverter()

            }
        }
    }
}



@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outoutUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue =  result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
                convertUnit()},
            label = {Text("Enter Value")},

            )
        Spacer(modifier = Modifier.height(16.dp))


        Row {
            //Input
            Box {
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(
                        text = {Text("CM")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "CM"
                            conversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                //output
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(outoutUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    oExpanded = false
                }) {
                    DropdownMenuItem(
                        text = { Text("CM") },
                        onClick = {
                            oExpanded = false
                            outoutUnit = "CM"
                            oConversionFactor.value = 0.01
                            convertUnit()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            oExpanded = false
                            outoutUnit = "Meter"
                            oConversionFactor.value = 1.00
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outoutUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeter") },
                        onClick = {
                            oExpanded = false
                            outoutUnit = "Milimeter"
                            oConversionFactor.value = 0.001
                            convertUnit()
                        }
                    )


                }
            }







//            val context = LocalContext.current
//
////            Button(onClick = {
////                Toast
////                    .makeText(context,
////                        "Thanks for clicking!",
////                        Toast.LENGTH_LONG).show()
////            }) {
////                Text("Click me")
////            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result Text
        Text("Result: $outputValue $outoutUnit",

        )
    }

}

@Preview
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}