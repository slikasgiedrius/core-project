package com.giedrius.baseproject.servers

import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.utils.mvp.BasePresenter

interface ServersContract {

    interface View {
        fun populateServers(servers: Array<Server>)
        fun setList()
        fun showLogin()
        fun showError(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun onCreated(servers: Array<Server>?)
        fun onLogoutClicked()
    }
}
