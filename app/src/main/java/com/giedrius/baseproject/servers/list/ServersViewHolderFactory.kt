package com.giedrius.baseproject.servers.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.giedrius.baseproject.R

class ServersViewHolderFactory {

    fun create(parent: ViewGroup): ServersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_server, parent, false)
        return ServersViewHolder(view)
    }
}
