package com.giedrius.baseproject.servers

import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.servers.ServersContract.View
import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.servers.network.ServersService
import com.giedrius.baseproject.utils.mvp.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo

class ServersPresenter(
        private val mainScheduler: Scheduler,
        private val tokenStorage: TokenStorage,
        private val serversService: ServersService
) : ServersContract.Presenter, ViewPresenter<View>() {

    override fun onLogoutClicked() {
        tokenStorage.removeToken()
        onView { showLogin() }
    }

    override fun onCreated(servers: Array<Server>?) {
        onView { setList() }
        servers?.let { nonNullServers ->
            onView { populateServers(nonNullServers) }
        } ?: serversService.getServers("Bearer ${tokenStorage.getToken()}")
                .observeOn(mainScheduler)
                .subscribe(::onServersReceived, ::onServersDownloadFailed)
                .addTo(subscription)
    }

    private fun onServersReceived(servers: List<Server>) {
        onView { populateServers(servers.toTypedArray()) }
    }

    private fun onServersDownloadFailed(throwable: Throwable) {
        onView { showError(throwable.localizedMessage) }
    }
}
