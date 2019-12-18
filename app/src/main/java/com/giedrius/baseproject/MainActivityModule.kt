package com.giedrius.baseproject

import com.giedrius.baseproject.login.LoginFragment
import com.giedrius.baseproject.login.LoginModule
import com.giedrius.baseproject.servers.ServersFragment
import com.giedrius.baseproject.servers.ServersModule
import com.giedrius.baseproject.utils.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ServersModule::class])
    abstract fun contributeServersFragment(): ServersFragment
}
