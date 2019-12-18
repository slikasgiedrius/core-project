package com.giedrius.baseproject.app

import com.giedrius.baseproject.MainActivity
import com.giedrius.baseproject.MainActivityModule
import com.giedrius.baseproject.utils.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppContributorsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
