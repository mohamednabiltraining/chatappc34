package com.route.chatappc34.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc34.R
import com.route.chatappc34.databinding.ActivityLoginBinding
import com.route.chatappc34.ui.home.HomeActivity
import com.route.chatappc34.ui.register.RegisterActivity
import com.route.islamigsun.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = viewModel
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewModel(): LoginViewModel {
       return ViewModelProvider(this).get(LoginViewModel::class.java)//initialize
    }

    override fun gotoRegister() {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun gotoHome() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}