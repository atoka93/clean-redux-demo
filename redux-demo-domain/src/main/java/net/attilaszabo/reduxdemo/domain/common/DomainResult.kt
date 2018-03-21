package net.attilaszabo.reduxdemo.domain.common

import java.io.Serializable

interface DomainResult<in TModel> : Serializable {

    fun success(result: TModel)

    fun error(throwable: Throwable?)
}
