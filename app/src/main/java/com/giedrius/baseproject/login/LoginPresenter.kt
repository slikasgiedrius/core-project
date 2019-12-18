package com.giedrius.baseproject.login

import com.giedrius.baseproject.login.LoginContract
import com.giedrius.baseproject.login.LoginContract.View
import com.giedrius.baseproject.login.network.LoginBody
import com.giedrius.baseproject.login.network.LoginService
import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.servers.network.ServersService
import com.giedrius.baseproject.utils.mvp.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class LoginPresenter(

    private val mainScheduler: Scheduler,
    private val loginService: LoginService,
    private val tokenStorage: TokenStorage,
    private val serversService: ServersService
) : LoginContract.Presenter, ViewPresenter<View>() {

    override fun onLoginClicked(username: String, password: String) {
        loginService.login(LoginBody(username, password))
            .observeOn(mainScheduler)
            .doOnSuccess { response ->
                tokenStorage.saveToken(response.token)
                onView { showLoadingView() }
            }
            .flatMap { response -> serversService.getServers("Bearer ${response.token}") }
            .observeOn(mainScheduler)
            .subscribe(::onServersDownloaded, ::onError)
            .addTo(subscription)
    }

    private fun onServersDownloaded(servers: List<Server>) {
        onView { showServers(servers) }
    }

    private fun onError(throwable: Throwable) {
        onView { showError(throwable.localizedMessage) }
        onView { hideLoadingView() }
        Timber.e(throwable)
    }
}
