package com.giedrius.baseproject.servers.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface ServersService {

    @GET("servers")
    fun getServers(@Header("Authorization") bearerToken: String): Single<List<Server>>
}
