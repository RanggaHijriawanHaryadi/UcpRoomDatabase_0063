package com.example.ucp2.repository

import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpr {

    suspend fun insertSuplier(suplier: Suplier)
    fun getAllSuplier(): Flow<List<Suplier>>
    

}