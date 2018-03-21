package net.attilaszabo.reduxdemo.data.repositories

import net.attilaszabo.reduxdemo.data.common.DataResult
import net.attilaszabo.reduxdemo.state.Repository

interface IRepositoriesSource {

    fun loadRepositories(loadFrom: Int, resultHandler: DataResult<List<Repository>>)
}
