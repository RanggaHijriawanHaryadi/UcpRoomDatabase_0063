package com.example.ucp2.depedanciesinjection

import android.content.Context
import com.example.ucp2.data.database.PenyimpananDatabase
import com.example.ucp2.repository.LocalRepositoryBrg
import com.example.ucp2.repository.LocalRepositorySpr
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.repository.RepositorySpr

interface InterfaceContainerApp{
    val repositorySpr: RepositorySpr
    val repositoryBrg: RepositoryBrg
}
class ContainerApp(private val context: Context): InterfaceContainerApp {
    override val repositorySpr: RepositorySpr by lazy {
        LocalRepositorySpr(PenyimpananDatabase.getDatabase(context).suplierDao())
    }

    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(PenyimpananDatabase.getDatabase(context).barangDao())
    }
}