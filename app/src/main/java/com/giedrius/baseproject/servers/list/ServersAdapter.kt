package com.giedrius.baseproject.servers.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giedrius.baseproject.servers.network.Server

class ServersAdapter(
        private val factory: ServersViewHolderFactory
) : RecyclerView.Adapter<ServersViewHolder>() {

    private val servers = mutableListOf<Server>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServersViewHolder {
        return factory.create(parent)
    }

    override fun getItemCount() = servers.size

    override fun onBindViewHolder(holder: ServersViewHolder, position: Int) {
        holder.bind(servers[position])
    }

    fun setAll(servers: List<Server>) {
        this.servers.clear()
        this.servers.addAll(servers)
        notifyDataSetChanged()
    }
}
