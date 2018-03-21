package net.attilaszabo.reduxdemo.data.sources.github

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiClient {

    // Members

    private val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service: GithubApiService = mRetrofit.create(GithubApiService::class.java)

    companion object {

        // Constants

        private const val BASE_URL = "https://api.github.com/"
    }
}