package com.giedrius.baseproject.servers.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.giedrius.baseproject.R
import com.giedrius.baseproject.servers.network.Server
import kotlinx.android.synthetic.main.item_server.view.country
import kotlinx.android.synthetic.main.item_server.view.distance

class ServersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(server: Server) {
        itemView.country.text = server.country
        val distanceLabelId = R.string.server_item_label_distance
        itemView.distance.text = view.context.getString(distanceLabelId, server.distance)
    }
}
