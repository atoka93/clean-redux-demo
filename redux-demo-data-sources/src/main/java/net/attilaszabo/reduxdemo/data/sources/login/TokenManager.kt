package net.attilaszabo.reduxdemo.data.sources.login

import net.attilaszabo.reduxdemo.data.common.DataResult
import net.attilaszabo.reduxdemo.data.login.IAccountsSource
import java.lang.IllegalArgumentException
import java.util.Random

class TokenManager : IAccountsSource {

    override fun grantNewAccessToken(username: String, password: String, resultHandler: DataResult<String>) {
        Thread {
            Thread.sleep(((2 + Random().nextInt(3)) * 500).toLong())
            if (Random().nextInt(2) == 1) {
                resultHandler.error(IllegalArgumentException("random exception"))
            } else {
                resultHandler.success("a token")
            }
        }.start()
    }
}
