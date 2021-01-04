package com.example.pruebaandroid.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pruebaandroid.R

class DetailFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val safeArgs: DetailFragmentArgs by navArgs()
        val transactionId = safeArgs.data
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}