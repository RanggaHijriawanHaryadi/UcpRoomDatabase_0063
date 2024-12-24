package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val deskripsi : String,
    val harga : Float,
    val stok : Int,
    val namaSuplier: String
)
