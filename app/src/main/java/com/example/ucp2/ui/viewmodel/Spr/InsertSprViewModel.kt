package com.example.ucp2.ui.viewmodel.Spr

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySpr
import kotlinx.coroutines.launch

class SuplierViewModel (private val repositorySpr: RepositorySpr): ViewModel() {
    var uiStateSpr by mutableStateOf(SprUIState())


    fun updateState(suplierEvent: SuplierEvent){
        uiStateSpr = uiStateSpr.copy(
            suplierEvent = suplierEvent,

            )
    }

    // validasi data input pengguna
    private fun validateFields():Boolean{
        val event = uiStateSpr.suplierEvent
        val errorState = FormErrorStateSpr(

            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak =  if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat =  if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",

        )
        uiStateSpr = uiStateSpr.copy(isEntryValidSpr = errorState)
        return  errorState.isValid()
    }


    fun saveData(){
        val currentEvent = uiStateSpr.suplierEvent


        if(validateFields()){
            viewModelScope.launch{
                try {
                    repositorySpr.insertSuplier(currentEvent.toSuplierEntity())
                    uiStateSpr = uiStateSpr.copy(
                        snackBarMessageSpr = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(),// Reset input tanpa form
                        isEntryValidSpr =  FormErrorStateSpr()// Reset error stetae
                    )
                }catch (e: Exception){
                    uiStateSpr = uiStateSpr.copy(
                        snackBarMessageSpr = " Data gagal disimpan"
                    )
                }
            }
        }else{
            uiStateSpr = uiStateSpr.copy(
                snackBarMessageSpr = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    fun resetSnackBarMessage(){
        uiStateSpr= uiStateSpr.copy(snackBarMessageSpr = null)
    }
}


data class  SprUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValidSpr: FormErrorStateSpr = FormErrorStateSpr(),
    val snackBarMessageSpr: String? = null,
)
data class FormErrorStateSpr(
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null,

    ){
    fun isValid(): Boolean{
        return  nama == null && kontak == null &&
                alamat == null
    }
}

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier (
    id = id?:0,
    nama = nama,
    kontak = kontak,
    alamat = alamat,

)
data class SuplierEvent(
    val id: Int? = null,
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",

)