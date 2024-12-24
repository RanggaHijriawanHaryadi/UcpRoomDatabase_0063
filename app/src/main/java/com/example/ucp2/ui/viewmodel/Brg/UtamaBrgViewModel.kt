package com.example.ucp2.ui.viewmodel.Brg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class UtamaBrgViewModel (
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    val utamaBrgUIState: StateFlow<UtamaBrgUiState> = repositoryBrg.getAllBarang()
        .filterNotNull()
        .map {
            UtamaBrgUiState(
                listBrg = it.toList(),
                isLoadingBrg = false,
            )
        }
        .onStart {
            emit(UtamaBrgUiState(isLoadingBrg = true))
            delay(900)
        }
        .catch {
            emit(
                UtamaBrgUiState(
                    isLoadingBrg = false,
                    isErrorBrg = true,
                    errorMessageBrg = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UtamaBrgUiState(
                isLoadingBrg = true,
            )
        )
}

data class UtamaBrgUiState(
    val listBrg: List<Barang> = listOf(),
    val isLoadingBrg: Boolean = false,
    val isErrorBrg: Boolean = false,
    val errorMessageBrg: String = ""
)