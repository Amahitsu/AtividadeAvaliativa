package com.example.atividadeavaliativa.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Calculator (
    val porcentFix: Float,

)

class CalculatorViewModel : ViewModel(){
    private val _billAmount = MutableStateFlow("")
    val billAmount: StateFlow<String> = _billAmount

    private val _tipPercentage = MutableStateFlow(18f)
    val tipPercentage: StateFlow<Float> = _tipPercentage

    fun updateBillAmout(amount: String){
        _billAmount.value = amount
    }

    fun updateTipPercentage(percentage: Float){
        _tipPercentage.value = percentage
    }

    fun calculateFixedTip(): Float{
        val bill = _billAmount.value.toFloatOrNull() ?: 0f
        return bill * 0.15f
    }

    fun calculateCustomTip(): Float{
        val bill = _billAmount.value.toFloatOrNull() ?: 0f
        return bill * (_tipPercentage.value/100)
    }

    fun calculateTotalFixedAmount(): Float {
        val bill = _billAmount.value.toFloatOrNull() ?: 0f
        val fixedTip = calculateFixedTip() // Porcentagem fixa de 15%
        return bill + fixedTip
    }

    fun calculateTotalCustomAmout(): Float{
        val bill = _billAmount.value.toFloatOrNull() ?: 0f
        val customTip = calculateCustomTip() // Porcentagem personalizada
        return bill + customTip
    }

}