package com.example.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.Brg.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.Spr.UtamaSprViewModel

object SprList {
    @Composable
    fun NamaSpr(
        dataSpr: UtamaSprViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>{
        val getNamaSpr by dataSpr.utamaSprUIState.collectAsState()
        val DataSpr = getNamaSpr.listSpr.map{it.nama}
        return DataSpr
    }
}