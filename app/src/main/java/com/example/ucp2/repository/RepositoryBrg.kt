package com.example.ucp2.repository

import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {

    suspend fun insertBarang(barang: Barang)
    fun getAllBarang(): Flow<List<Barang>>
    fun getBarang(id: Int) : Flow<Barang>
    suspend fun updateBarang(barang: Barang)
    suspend fun deleteBarang(barang: Barang)
}