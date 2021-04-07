package com.route.chatappc34.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc34.R
import com.route.chatappc34.databinding.ActivityRegisterBinding
import com.route.chatappc34.ui.home.HomeActivity
import com.route.islamigsun.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>() ,Navigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun gotoHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}