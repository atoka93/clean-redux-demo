package net.attilaszabo.reduxdemo.domain.login.data

import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.login.LoginActions.LoggedIn

interface IAccountsRepository {

    fun grantNewAccessToken(username: String, password: String, resultHandler: DomainResult<LoggedIn>)
}
