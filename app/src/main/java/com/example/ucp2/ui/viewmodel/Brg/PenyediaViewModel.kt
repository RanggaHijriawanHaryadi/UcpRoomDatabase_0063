package com.example.ucp2.ui.viewmodel.Brg

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.App
import com.example.ucp2.ui.viewmodel.Spr.SuplierViewModel
import com.example.ucp2.ui.viewmodel.Spr.UtamaSprViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        //Barang
        initializer {
            BarangViewModel(
                App().containerApp.repositoryBrg
            )
        }

        initializer {
            UtamaBrgViewModel (
                App().containerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                App().containerApp.repositoryBrg
            )
        }

        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                App().containerApp.repositoryBrg
            )
        }

        //Suplier

        initializer {
            SuplierViewModel(
                App().containerApp.repositorySpr
            )
        }

        initializer {
            UtamaSprViewModel (
                App().containerApp.repositorySpr
            )
        }

    }
}

fun CreationExtras.App():App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as App)
