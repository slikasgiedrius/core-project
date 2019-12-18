package com.giedrius.baseproject.login

import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.utils.mvp.BasePresenter

interface LoginContract {

    interface View {
        fun showError(message: String)
        fun showLoadingView()
        fun showServers(servers: List<Server>)
        fun hideLoadingView()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(username: String, password: String)
    }
}
