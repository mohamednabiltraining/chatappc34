package com.route.chatappc34.ui.addRoom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc34.R
import com.route.chatappc34.databinding.ActivityAddRoomBinding
import com.route.islamigsun.base.BaseActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel;
        viewModel.navigator=this
        viewModel.roomAdded.observe(this, Observer {
            showDialoge(message = "room Added successfully",
                posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                },
                isCancelable = false

            )
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
}