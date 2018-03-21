package net.attilaszabo.reduxdemo.data.repositories

import net.attilaszabo.reduxdemo.data.common.DataResult
import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.repositories.RepositoriesActions.Results
import net.attilaszabo.reduxdemo.domain.repositories.data.IRepositoriesRepository
import net.attilaszabo.reduxdemo.state.Repository

class RepositoriesRepository(private val mRepositoriesSource: IRepositoriesSource) : IRepositoriesRepository {

    override fun loadRepositories(loadFrom: Int, resultHandler: DomainResult<Results>) {
        val sourceResultHandler = object : DataResult<List<Repository>> {
            override fun success(result: List<Repository>?) {
                result?.let { resultHandler.success(Results(result, result.size < 100)) }
            }

            override fun error(throwable: Throwable?) {
                resultHandler.error(throwable)
            }
        }
        mRepositoriesSource.loadRepositories(loadFrom, sourceResultHandler)
    }
}
