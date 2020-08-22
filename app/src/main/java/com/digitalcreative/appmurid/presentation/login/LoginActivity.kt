package com.digitalcreative.appmurid.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.preferences.UserPreferences
import com.digitalcreative.appmurid.presentation.home.HomeActivity
import com.digitalcreative.appmurid.utils.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()
    private val loadingDialog by loadingDialog()

    @Inject
    lateinit var preferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (viewModel.isUserLoggedIn()) {
            moveToHome()
            finish()
        }

        initObserver()

        btn_login.setOnClickListener {
            val emailNis = edt_nis_email.text.toString().trim()
            val password = edt_password.text.toString().trim()
            viewModel.login(emailNis, password)
        }
    }

    private fun initObserver() {
        viewModel.loading.observe(this, Observer(this::showLoading))
        viewModel.data.observe(this, Observer(this::moveToHome))
        viewModel.message.observe(this, Observer(this::showErrorMessage))
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            loadingDialog.showDialog()
        } else {
            loadingDialog.closeDialog()
        }
    }

    private fun moveToHome(student: Student? = null) {
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }

    private fun showErrorMessage(message: String) {
        Toasty.error(this, message, Toasty.LENGTH_LONG, true).show()
    }
}