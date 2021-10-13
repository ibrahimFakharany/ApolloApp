package com.example.apollographqltutorial.networking

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class ProductsApi {
    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.builder()
            .serverUrl("https://mcstaging.thegrocer.net/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
}