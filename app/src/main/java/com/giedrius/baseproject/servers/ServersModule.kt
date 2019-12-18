package com.giedrius.baseproject.servers

import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.servers.list.ServersAdapter
import com.giedrius.baseproject.servers.list.ServersViewHolderFactory
import com.giedrius.baseproject.servers.network.ServersService
import com.giedrius.baseproject.utils.schedulers.Main
import com.giedrius.baseproject.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
abstract class ServersModule {

    @Module
    companion object {
        @FragmentScope
        @JvmStatic @Provides
        fun providePresenter(
                tokenStorage: TokenStorage,
                @Main mainScheduler: Scheduler,
                serversService: ServersService
        ): ServersContract.Presenter {
            return ServersPresenter(
                mainScheduler,
                tokenStorage,
                serversService
            )
        }

        @JvmStatic @Provides
        fun provideServersAdapter(): ServersAdapter = ServersAdapter(ServersViewHolderFactory())
    }
}
