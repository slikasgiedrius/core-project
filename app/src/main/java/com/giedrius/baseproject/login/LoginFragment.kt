package com.giedrius.baseproject.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.giedrius.baseproject.R
import com.giedrius.baseproject.base.BaseDaggerFragment
import com.giedrius.baseproject.servers.ServersFragment
import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : BaseDaggerFragment(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            presenter.onLoginClicked(
                usernameInput.text.toString(),
                passwordInput.text.toString()
            )
        }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId() = R.layout.fragment_login

    override fun showLoadingView() {
        val loadingConstraintSet = ConstraintSet().apply {
            clone(requireContext(), R.layout.fragment_login_loading)
        }
        loadingConstraintSet.applyTo(root_layout)
    }

    override fun hideLoadingView() {
        val initialLoginConstraintSet = ConstraintSet().apply {
            clone(requireContext(), R.layout.fragment_login)
        }
        initialLoginConstraintSet.applyTo(root_layout)
    }

    override fun showServers(servers: List<Server>) {
        val generalErrorMessage = getString(R.string.general_error_something_wrong)
        activity?.replaceFragment(ServersFragment.newInstance(servers))
            ?: Toast.makeText(requireContext(), generalErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
