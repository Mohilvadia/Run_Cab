package com.runcab

import android.app.Application

class App : Application() {

    companion object {
        private var instance: App? = null

        @JvmStatic
        fun getInstance(): App {
            return instance as App
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}