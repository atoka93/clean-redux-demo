package net.attilaszabo.reduxdemo.domain.repositories.data

import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.repositories.RepositoriesActions.Results

interface IRepositoriesRepository {

    fun loadRepositories(loadFrom: Int, resultHandler: DomainResult<Results>)
}
