package com.example.ucp2.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
): RepositoryBrg {

    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }
    override fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }
    override fun getBarang(id: String): Flow<Barang> {
        return barangDao.getBarang(id)
    }
    override suspend fun updateBarang(barang: Barang) {
        return barangDao.updateBarang(barang)
    }
    override suspend fun deleteBarang(barang: Barang) {
        return barangDao.deleteBarang(barang)
    }

}