package com.route.chatappc34.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.chatappc34.R
import com.route.chatappc34.database.model.Room
import com.route.chatappc34.databinding.ActivityHomeBinding
import com.route.chatappc34.ui.roomDetailsActivity.RoomDetailsActivity
import com.route.chatappc34.ui.addRoom.AddRoomActivity
import com.route.islamigsun.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {
    lateinit var roomsAdapter :RoomsRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this
        setUpViews()
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.roomsLiveData.observe(this, Observer {
            roomsAdapter.changeData(it)
        })
    }
    override fun onStart() {
        super.onStart()
        viewModel.getRoomsList()
    }
    private fun setUpViews() {
        roomsAdapter = RoomsRecyclerAdapter(listOf())
        roomsAdapter.onItemClickListener = object :RoomsRecyclerAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, room: Room) {
                val intent = Intent(this@HomeActivity,
                    RoomDetailsActivity::class.java)
                intent.putExtra("room",room);
                startActivity(intent)
            }
        }
        viewDataBinding.roomsRecyclerView.adapter =roomsAdapter
        val layoutManager = LinearLayoutManager(this);
        viewDataBinding.roomsRecyclerView.layoutManager
        viewDataBinding.roomsRecyclerView.layoutManager=layoutManager
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun openAddRoom() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }
}