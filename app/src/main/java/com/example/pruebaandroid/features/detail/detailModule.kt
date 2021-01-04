package com.example.pruebaandroid.features.detail

import com.example.pruebaandroid.base.ui.SharedViewModel
import com.example.pruebaandroid.features.detail.ui.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var detailModule = module {
    viewModel { (sku: String, sharedViewModel: SharedViewModel) ->
        DetailViewModel(
            sku,
            sharedViewModel
        )
    }
}