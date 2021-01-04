package com.example.pruebaandroid.features.detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.base.ui.SharedViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

private const val AUD = "AUD"
private const val CAD = "CAD"
private const val USD = "USD"
private const val EUR = "EUR"

class DetailViewModel(
    private val sku: String,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {
    val detailTitle = MutableLiveData(sku)
    val total = MutableLiveData("0.0")
    val transactionList = MutableLiveData<List<TransactionModel>>()
    private var audToEurRatio = 0f
    private var usdToAutRatio = 0f
    private var cadToUsdRatio = 0f
    private val decimalFormat = DecimalFormat("#.##")

    init {
        sharedViewModel.transactionsList?.let { list ->
            val tempList = list.filter { it.sku == sku }
            transactionList.value = tempList
            getTotalValueInEUR(tempList)
        }
        sharedViewModel.ratesList?.find { it.from == AUD && it.to == EUR }?.let {
            audToEurRatio = it.rate.toFloat()
        }
        sharedViewModel.ratesList?.find { it.from == USD && it.to == AUD }?.let {
            usdToAutRatio = it.rate.toFloat()
        }
        sharedViewModel.ratesList?.find { it.from == CAD && it.to == USD }?.let {
            cadToUsdRatio = it.rate.toFloat()
        }
        decimalFormat.roundingMode = RoundingMode.HALF_EVEN
    }

    private fun getTotalValueInEUR(list: List<TransactionModel>) {
        val totalValue = list.map {
            when (it.currency) {
                AUD -> {
                    audToEur(it.amount.toFloat())
                }
                CAD -> {
                    cadToEur(it.amount.toFloat())
                }
                USD -> {
                    usdToEur(it.amount.toFloat())
                }
                else -> {
                    it.amount.toDouble()
                }
            }
        }.sumByDouble { it }
        total.value = String.format("%.2f", totalValue)
    }

    private fun usdToEur(amount: Float): Double {
        val convertedValue = amount * usdToAutRatio * audToEurRatio
        return decimalFormat.format(convertedValue.toDouble()).toDouble()
    }

    private fun cadToEur(amount: Float): Double {
        val convertedValue = amount * cadToUsdRatio * usdToAutRatio * audToEurRatio
        return decimalFormat.format(convertedValue.toDouble()).toDouble()
    }

    private fun audToEur(amount: Float): Double {
        val convertedValue = amount * audToEurRatio
        return decimalFormat.format(convertedValue.toDouble()).toDouble()
    }
}