package com.digitalcreative.appmurid.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.presentation.ui.home.HomeActivity
import com.digitalcreative.appmurid.utils.helper.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()
    private val loadingDialog by loadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (viewModel.isUserLoggedIn()) {
            moveToHome(true)
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
        viewModel.message.observe(this, Observer(this::showMessage))
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            if (!loadingDialog.isAdded) {
                loadingDialog.showDialog()
            }
        } else {
            if (loadingDialog.isAdded) {
                loadingDialog.closeDialog()
            }
        }
    }

    private fun moveToHome(shouldMove: Boolean) {
        if (shouldMove) {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }

    private fun showMessage(message: String) {
        Toasty.error(this, message, Toasty.LENGTH_LONG, true).show()
    }
}