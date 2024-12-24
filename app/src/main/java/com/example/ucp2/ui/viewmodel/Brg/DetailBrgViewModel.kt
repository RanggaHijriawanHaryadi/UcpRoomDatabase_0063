package com.example.ucp2.ui.viewmodel.Brg

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.ui.navigation.DestinasiDetailBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
): ViewModel(){
    private val _id: Int = checkNotNull(savedStateHandle[DestinasiDetailBrg.ID])

    val detailUIState: StateFlow<DetailUiState> = repositoryBrg.getBarang(_id)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoadingBrg = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoadingBrg = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoadingBrg = false,
                    isErrorBrg = true,
                    errorMessageBrg = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoadingBrg = true,
            ),
        )
    fun deleteBrg(){
        detailUIState.value.detailUiEvent.toBarangEntity().let {
            viewModelScope.launch{
                repositoryBrg.deleteBarang(it)
            }
        }
    }
}
data class DetailUiState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoadingBrg: Boolean = false,
    val isErrorBrg: Boolean = false,
    val errorMessageBrg: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == BarangEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != BarangEvent()
}


fun Barang.toDetailUiEvent(): BarangEvent {
    return BarangEvent(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga.toString(),
        stok = stok.toString(),
        namaSuplier = namaSuplier
    )
}

