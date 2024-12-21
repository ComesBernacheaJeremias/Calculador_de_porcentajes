package com.example.calcularporcentaje

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcularporcentaje.ui.theme.CalcularPorcentajeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcularPorcentajeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MiUI()
                }
            }
        }
    }
}

@Composable
private fun MiUI() {
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) } // Estado para controlar el diálogo



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Calcular Porcentaje", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        // Input Fields
        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text("Numero") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text("Porcentaje") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "¿Que Quieres Hacer?", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (value1.isEmpty() || value2.isEmpty()) {
                        Toast.makeText(context,
                        "Complete todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()}else{
                    val num1 = value1.toDoubleOrNull() ?: 0.0
                    val num2 = value2.toDoubleOrNull() ?: 0.0
                    val num3 = num2 / 100 + 1
                    result = "${num1 * num3}"
                    showDialog = true // Mostrar diálogo
                }},
                modifier = Modifier.weight(1f)
            ) {
                Text("Sumar")
            }

            Button(
                onClick = {
                    if (value1.isEmpty() || value2.isEmpty()) {
                        Toast.makeText(context,
                            "Complete todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()}else{
                    val num1 = value1.toDoubleOrNull() ?: 0.0
                    val num2 = value2.toDoubleOrNull() ?: 0.0
                    val num3 = num2 / 100
                    result = "${num1 * num3}"
                    showDialog = true // Mostrar diálogo
                }},
                modifier = Modifier.weight(1f)
            ) {
                Text("Conseguir")
            }

            Button(
                onClick = {
                    if (value1.isEmpty() || value2.isEmpty()) {
                        Toast.makeText(context,
                            "Complete todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()}else{
                    val num1 = value1.toDoubleOrNull() ?: 0.0
                    val num2 = value2.toDoubleOrNull() ?: 0.0
                    val num3 = num2 / 100
                    val porcentaje = num1 * num3
                    result = "${num1 - porcentaje}"
                    showDialog = true // Mostrar diálogo
                }},
                modifier = Modifier.weight(1f)
            ) {
                Text("Restar")
            }
        }


        // Diálogo para mostrar el resultado
        if (showDialog && value1.isNotEmpty() && value2.isNotEmpty()) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Resultado") },
                text = { Text(result) }
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalcularPorcentajeTheme {
       MiUI()
    }
}