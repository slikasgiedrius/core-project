package com.giedrius.baseproject

import android.os.Bundle
import com.giedrius.baseproject.dagger.BaseDaggerActivity
import com.giedrius.baseproject.login.LoginFragment
import com.giedrius.baseproject.repository.TokenStorage
import com.giedrius.baseproject.servers.ServersFragment
import com.giedrius.baseproject.utils.extensions.replaceFragment
import javax.inject.Inject

class MainActivity : BaseDaggerActivity() {

    @Inject
    lateinit var tokenStorage: TokenStorage

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            if (tokenStorage.getToken().isEmpty()) {
                replaceFragment(LoginFragment.newInstance())
            } else {
                replaceFragment(ServersFragment.newInstance())
            }
        }
    }
}