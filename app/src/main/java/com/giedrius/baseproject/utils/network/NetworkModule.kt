package com.giedrius.baseproject.utils.network

import com.giedrius.baseproject.BuildConfig
import com.giedrius.baseproject.login.network.LoginService
import com.giedrius.baseproject.servers.network.ServersService
import com.giedrius.baseproject.utils.Constants
import com.giedrius.baseproject.utils.schedulers.Io
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient, @Io scheduler: Scheduler): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                .build()
        }

        @JvmStatic
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        @JvmStatic
        @Provides
        fun provideLoginService(retrofit: Retrofit): LoginService {
            return retrofit.create(LoginService::class.java)
        }

        @JvmStatic
        @Provides
        fun provideServersService(retrofit: Retrofit): ServersService {
            return retrofit.create(ServersService::class.java)
        }
    }
}
