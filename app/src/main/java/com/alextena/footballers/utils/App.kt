package com.alextena.footballers.utils

import android.app.Application


class App : Application() {

    companion object{
        private lateinit var instance : App
        fun getContext() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}