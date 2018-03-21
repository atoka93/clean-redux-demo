package net.attilaszabo.reduxdemo.login

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.activityLoginErrorTextView
import kotlinx.android.synthetic.main.activity_login.activityLoginLoginButton
import kotlinx.android.synthetic.main.activity_login.activityLoginPasswordEditText
import kotlinx.android.synthetic.main.activity_login.activityLoginProgressBar
import kotlinx.android.synthetic.main.activity_login.activityLoginUsernameEditText
import net.attilaszabo.reduxdemo.R
import net.attilaszabo.reduxdemo.common.base.BaseActivity
import net.attilaszabo.reduxdemo.common.setVisibility
import net.attilaszabo.reduxdemo.login.LoginViewModel.ReactionListener
import net.attilaszabo.reduxdemo.repositories.RepositoriesActivity

class LoginActivity : BaseActivity<LoginViewModel>(), ReactionListener {

    // AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginLoginButton.setOnClickListener {
            mViewModel.onLoginButtonPressed(
                    activityLoginUsernameEditText.text.toString(),
                    activityLoginPasswordEditText.text.toString()
            )
        }
    }

    // BaseActivity

    override fun getLayoutResourceId() = R.layout.activity_login

    override fun onCreateViewModel() = LoginViewModel(this)

    // ReactionListener

    override fun onLoginDone() {
        startActivity(Intent(this, RepositoriesActivity::class.java))
        this.finish()
    }

    override fun setError(message: String) {
        activityLoginErrorTextView.text = message
    }

    override fun adjustLoadingIndicatorVisibility(isVisible: Boolean) {
        activityLoginProgressBar.setVisibility(isVisible)
    }
}
