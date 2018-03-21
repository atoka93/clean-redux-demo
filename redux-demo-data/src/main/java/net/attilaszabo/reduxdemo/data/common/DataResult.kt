package net.attilaszabo.reduxdemo.data.common

import java.io.Serializable

interface DataResult<in TModel> : Serializable {

    fun success(result: TModel?)

    fun error(throwable: Throwable?)
}
