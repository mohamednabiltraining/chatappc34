package com.route.chatappc34.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chatappc34.R
import com.route.chatappc34.database.model.Room
import com.route.chatappc34.databinding.LayoutRoomBinding

class RoomsRecyclerAdapter(var roomsList:List<Room>) :RecyclerView.Adapter<RoomsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutRoomBinding:LayoutRoomBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_room,parent,false);
        return ViewHolder(layoutRoomBinding)
    }

    override fun getItemCount(): Int =
        roomsList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room= roomsList.get(position)
        holder.bind(room)
        if(onItemClickListener!=null)
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,room)
            }
    }
    fun changeData(roomsList: List<Room>){
        this.roomsList=roomsList
        notifyDataSetChanged()
    }
    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position: Int,room:Room)
    }
    class ViewHolder(val itemBinding:LayoutRoomBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(room: Room){
            itemBinding.room=room
        }
    }
}