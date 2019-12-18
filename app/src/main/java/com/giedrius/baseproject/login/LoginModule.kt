package com.giedrius.baseproject.login

import com.giedrius.baseproject.login.network.LoginService
import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.servers.network.ServersService
import com.giedrius.baseproject.utils.schedulers.Main
import com.giedrius.baseproject.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
abstract class LoginModule {

    @Module
    companion object {
        @FragmentScope
        @JvmStatic @Provides
        fun providePresenter(
            loginService: LoginService,
            tokenStorage: TokenStorage,
            serversService: ServersService,
            @Main mainScheduler: Scheduler
        ): LoginContract.Presenter {
            return LoginPresenter(
                mainScheduler,
                loginService,
                tokenStorage,
                serversService
            )
        }
    }
}
