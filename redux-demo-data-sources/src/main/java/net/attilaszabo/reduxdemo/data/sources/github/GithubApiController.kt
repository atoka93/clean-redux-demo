package net.attilaszabo.reduxdemo.data.sources.github

import net.attilaszabo.reduxdemo.data.common.DataResult
import net.attilaszabo.reduxdemo.data.repositories.IRepositoriesSource
import net.attilaszabo.reduxdemo.data.sources.github.repositories.GithubRepository
import net.attilaszabo.reduxdemo.state.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubApiController : IRepositoriesSource {

    override fun loadRepositories(loadFrom: Int, resultHandler: DataResult<List<Repository>>) =
            GithubApiClient().service.getRepositories(loadFrom)
                    .enqueue(object : Callback<List<GithubRepository>> {
                        override fun onResponse(call: Call<List<GithubRepository>>?, response: Response<List<GithubRepository>>?) {
                            if (response?.body() == null) {
                                onFailure(call, null)
                            } else {
                                resultHandler.success(response.body()?.map {
                                    Repository(it.id, it.name)
                                })
                            }
                        }

                        override fun onFailure(call: Call<List<GithubRepository>>?, throwable: Throwable?) {
                            resultHandler.error(throwable)
                        }
                    })
}
