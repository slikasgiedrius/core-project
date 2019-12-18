package com.giedrius.baseproject.servers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.giedrius.baseproject.R
import com.giedrius.baseproject.dagger.BaseDaggerFragment
import com.giedrius.baseproject.login.LoginFragment
import com.giedrius.baseproject.servers.list.ServersAdapter
import com.giedrius.baseproject.servers.network.Server
import com.giedrius.baseproject.utils.values.Constants
import com.giedrius.baseproject.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_servers.iconLogout
import kotlinx.android.synthetic.main.fragment_servers.serversList
import javax.inject.Inject

class ServersFragment : BaseDaggerFragment(), ServersContract.View {

    @Inject lateinit var presenter: ServersContract.Presenter
    @Inject lateinit var adapter: ServersAdapter

    override fun getLayoutId() = R.layout.fragment_servers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreated(arguments?.getParcelableArray(Constants.KEY_SERVERS) as Array<Server>?)
        iconLogout.setOnClickListener { presenter.onLogoutClicked() }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun showLogin() {
        val generalErrorMessage = getString(R.string.general_error_something_wrong)
        activity?.replaceFragment(LoginFragment.newInstance())
                ?: Toast.makeText(requireContext(), generalErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun setList() {
        serversList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        serversList.setHasFixedSize(true)
        serversList.adapter = adapter
    }

    override fun populateServers(servers: Array<Server>) {
        adapter.setAll(servers.toList())
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    companion object {
        fun newInstance(servers: List<Server>? = null): ServersFragment {
            return ServersFragment().apply {
                arguments = Bundle().apply { putParcelableArray(Constants.KEY_SERVERS, servers?.toTypedArray()) }
            }
        }
    }
}
