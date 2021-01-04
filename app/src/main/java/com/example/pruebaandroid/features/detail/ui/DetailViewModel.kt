package com.example.pruebaandroid.features.detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebaandroid.base.domain.TransactionModel
import com.example.pruebaandroid.base.ui.SharedViewModel
import java.math.BigDecimal
import java.math.RoundingMode

private const val AUD = "AUD"
private const val CAD = "CAD"
private const val USD = "USD"
private const val EUR = "EUR"

class DetailViewModel(
    private val sku: String,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {
    val detailTitle = MutableLiveData(sku)
    val total = MutableLiveData(0.0)
    val transactionList = MutableLiveData<List<TransactionModel>>()
    private var audToEurRatio = 0f
    private var usdToAutRatio = 0f
    private var cadToUsdRatio = 0f

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
    }

    private fun getTotalValueInEUR(list: List<TransactionModel>) {
        total.value = list.map {
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
                    it.amount.toFloat()
                }
            }
        }.sumOf { BigDecimal(it.toDouble()).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
    }

    private fun usdToEur(amount: Float): Float {
        return amount * usdToAutRatio * audToEurRatio
    }

    private fun cadToEur(amount: Float): Float {
        return amount * cadToUsdRatio * usdToAutRatio * audToEurRatio
    }

    private fun audToEur(amount: Float): Float {
        return amount * audToEurRatio
    }
}