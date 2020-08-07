package com.example.solgikb.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.data.model.Result
import com.example.solgikb.databinding.ActivityLoginBinding
import com.example.solgikb.ui.base.BaseActivity
import com.example.solgikb.ui.main.MainActivity
import com.example.solgikb.utils.REQUEST_CODE_GOOGLE_SIGN_IN
import com.example.solgikb.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResId: Int get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun initView() {
        binding.googleLogin.setOnClickListener {
            viewModel.googleSignIn().also {
                startActivityForResult(it, REQUEST_CODE_GOOGLE_SIGN_IN)
            }
        }
    }

    override fun observeChange() {
        observe(viewModel.userLiveData) { it ->
            if(it is Result.Success) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleOnActivityResult(requestCode, resultCode, data)
    }

}