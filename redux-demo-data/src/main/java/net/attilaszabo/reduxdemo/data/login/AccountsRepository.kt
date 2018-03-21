package net.attilaszabo.reduxdemo.data.login

import net.attilaszabo.reduxdemo.data.common.DataResult
import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.login.LoginActions.LoggedIn
import net.attilaszabo.reduxdemo.domain.login.data.IAccountsRepository

class AccountsRepository(private val mAccountsSource: IAccountsSource) : IAccountsRepository {

    override fun grantNewAccessToken(username: String, password: String, resultHandler: DomainResult<LoggedIn>) {
        val sourceResultHandler = object : DataResult<String> {
            override fun success(result: String?) {
                result?.let { resultHandler.success(LoggedIn(result)) }
            }

            override fun error(throwable: Throwable?) {
                resultHandler.error(throwable)
            }
        }
        mAccountsSource.grantNewAccessToken(username, password, sourceResultHandler)
    }
}
