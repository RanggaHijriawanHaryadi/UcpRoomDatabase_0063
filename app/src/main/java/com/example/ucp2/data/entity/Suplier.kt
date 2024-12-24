package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val kontak : String,
    val alamat : String

)
