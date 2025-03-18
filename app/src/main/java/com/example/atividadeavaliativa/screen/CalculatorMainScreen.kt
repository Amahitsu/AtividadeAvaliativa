package com.example.atividadeavaliativa.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun CalculatorApp(viewModel: CalculatorViewModel = viewModel()) {
    val billAmount by viewModel.billAmount.collectAsState()
    val tipPercentage by viewModel.tipPercentage.collectAsState()

    val fixedTip = viewModel.calculateFixedTip()
    val customTip = viewModel.calculateCustomTip()
    val fixedTotal = viewModel.calculateTotalFixedAmount()
    val customTotal = viewModel.calculateTotalCustomAmout()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Calculadora de Gorjeta", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Valor da conta  ")
            OutlinedTextField(
                value = billAmount,
                onValueChange = { viewModel.updateBillAmout(it) },
                label = { Text("R$ Valor") }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //Exibe a porcentagem antes da alteração no slider.
            Text(
                text = "% Personalizada: ",
                fontSize = 18.sp
            )

            //Slider interativo
            Slider(
                value = tipPercentage,
                onValueChange = { viewModel.updateTipPercentage(it) },
                valueRange = 0f..30f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )


        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top, // Alinha as colunas no topo
        ) {

            //Demonstra a gorjeta fixa
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Alinha os itens da coluna no centro
                modifier = Modifier.weight(1f) // Faz a coluna ocupar metade da largura disponível
            ) {
                Text(text = "Gorjeta Fixa (15%):")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(" R$ ${"%.2f".format(fixedTip)}") },
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(text="Valor total")
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text(" R$ ${"%.2f".format(fixedTotal)}")}
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Alinha os itens da coluna no centro
                modifier = Modifier.weight(1f) // Faz a coluna ocupar metade da largura disponível
            ) {

                Text(text = "Gorjeta Pers. (${"%.0f%%".format(tipPercentage)}):")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text(" R$ ${"%.2f".format(customTip)}")}
                )

                Spacer(modifier = Modifier.height(16.dp))
                Column {

                Text(text="Valor total")

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {Text(" R$ ${"%.2f".format(customTotal)}")}
                )
                }
            }
        }
    }
}
