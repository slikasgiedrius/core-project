package com.giedrius.baseproject.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
    }
}
