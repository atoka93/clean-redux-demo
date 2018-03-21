package net.attilaszabo.reduxdemo.data.login

import net.attilaszabo.reduxdemo.data.common.DataResult

interface IAccountsSource {

    fun grantNewAccessToken(username: String, password: String, resultHandler: DataResult<String>)
}
