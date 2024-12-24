package com.example.ucp2.ui.viewmodel.Spr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySpr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class UtamaSprViewModel (
    private val repositorySpr: RepositorySpr
) : ViewModel(){

    val utamaSprUIState: StateFlow<UtamaSprUiState> = repositorySpr.getAllSuplier()
        .filterNotNull()
        .map {
            UtamaSprUiState(
                listSpr = it.toList(),
                isLoadingSpr = false,
            )
        }
        .onStart {
            emit(UtamaSprUiState(isLoadingSpr = true))
            delay(900)
        }
        .catch {
            emit(
                UtamaSprUiState(
                    isLoadingSpr = false,
                    isErrorSpr = true,
                    errorMessageSpr = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UtamaSprUiState(
                isLoadingSpr = true,
            )
        )
}

data class UtamaSprUiState(
    val listSpr: List<Suplier> = listOf(),
    val isLoadingSpr: Boolean = false,
    val isErrorSpr: Boolean = false,
    val errorMessageSpr: String = ""
)