package com.example.pruebaandroid.features.detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.base.ui.SharedViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class DetailViewModel(
    private val sku: String,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {
    val detailTitle = MutableLiveData(sku)
    val total = MutableLiveData("0.0")
    val transactionList = MutableLiveData<List<TransactionModel>>()
    private val decimalFormat = DecimalFormat("#.##")

    init {
        sharedViewModel.transactionsList?.let { list ->
            val tempList = list.filter { it.sku == sku }
            transactionList.value = tempList
            getTotalValueInEUR(tempList)
        }
        decimalFormat.roundingMode = RoundingMode.HALF_EVEN
    }

    private fun getTotalValueInEUR(list: List<TransactionModel>) {
        val totalValue = list.map {
            recursiva(it.currency, it.amount.toDouble())
        }.sumByDouble { it }
        total.value = String.format("%.2f", totalValue)
    }

    private fun recursiva(
        currency: String,
        value: Double
    ): Double {
        var actualCurrency = currency
        var lastCurrency = ""
        var tempValue = value
        println("entrada -> $actualCurrency")
        while (actualCurrency != "EUR") {
            val ratio =
                sharedViewModel.ratesList?.find {
                    it.from == actualCurrency && it.to != lastCurrency
                }
            println(ratio)
            tempValue *= ratio!!.rate.toDouble()
            lastCurrency = ratio.from
            actualCurrency = ratio.to
        }
        return tempValue
    }
}