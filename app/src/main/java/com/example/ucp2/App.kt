package com.example.ucp2

import android.app.Application
import com.example.ucp2.depedanciesinjection.ContainerApp

class App : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp( this)
    }
}