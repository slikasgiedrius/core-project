package com.giedrius.baseproject.app

import android.content.Context
import android.preference.PreferenceManager
import com.giedrius.baseproject.repository.PreferencesTokenStorage
import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.utils.schedulers.Io
import com.giedrius.baseproject.utils.schedulers.Main
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(applicationContext: BaseApplication): Context

    @Module
    companion object {
        @JvmStatic @Provides @Io
        fun provideIoScheduler(): Scheduler = Schedulers.io()

        @JvmStatic @Provides @Main
        fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

        @JvmStatic @Provides
        fun provideTokenStorage(context: Context): TokenStorage {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return PreferencesTokenStorage(sharedPreferences)
        }
    }
}
