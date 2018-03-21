package net.attilaszabo.reduxdemo.data.sources.github

import net.attilaszabo.reduxdemo.data.sources.github.repositories.GithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") user: String): Call<List<GithubRepository>>

    @GET("repositories")
    fun getRepositories(@Query("since") since: Int): Call<List<GithubRepository>>
}
