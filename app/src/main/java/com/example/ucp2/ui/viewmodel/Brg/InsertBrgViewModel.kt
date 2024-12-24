package com.example.ucp2.ui.viewmodel.Brg

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.launch

class BarangViewModel (
    private val repositoryBrg: RepositoryBrg
) : ViewModel(){
    var uiStateBrg by mutableStateOf(BrgUIState())


    fun updateState(barangEvent: BarangEvent) {
        uiStateBrg = uiStateBrg.copy(
            barangEvent = barangEvent,
            )
    }

    private fun validateFields(): Boolean{
        val event = uiStateBrg.barangEvent
        val errorState =  FormErrorStateBrg(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Suplier tidak boleh kosong",
        )

        uiStateBrg = uiStateBrg.copy(isEntryValid = errorState)
        return errorState.isValid()
    }


    fun saveData(){
        val currentEvent = uiStateBrg.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBarang(currentEvent.toBarangEntity())
                    uiStateBrg = uiStateBrg.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorStateBrg(),
                    )
                } catch (e: Exception) {
                    uiStateBrg = uiStateBrg.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiStateBrg = uiStateBrg.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    fun resetSnackBarMessage() {
        uiStateBrg = uiStateBrg.copy(snackBarMessage = null)
    }
}

data class  BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorStateBrg = FormErrorStateBrg(),
    val snackBarMessage: String? = null,
)

data class FormErrorStateBrg(
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null,

    ){
    fun isValid(): Boolean{
        return  nama == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }
}


fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id?: 0,
    nama = nama,
    deskripsi = deskripsi,
    harga = (harga.toFloatOrNull() ?:0.0) as Float,
    stok = stok.toIntOrNull()?:0,
    namaSuplier = namaSuplier
)


data class BarangEvent(
    val id: Int? = null,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaSuplier: String = "",
)