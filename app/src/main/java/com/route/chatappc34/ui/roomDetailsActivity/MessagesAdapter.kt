package com.route.chatappc34.ui.roomDetailsActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chatappc34.Data
import com.route.chatappc34.R
import com.route.chatappc34.database.model.Message
import com.route.chatappc34.databinding.LayoutMessageRecievedBinding
import com.route.chatappc34.databinding.LayoutMessageSentBinding

class MessagesAdapter(var messagesList:MutableList<Message>) :RecyclerView.Adapter<MessageViewHolder>() {

    val SENT_MESSAGE_TYPE =0;
    val RECIEVED_MESSAGE_TYPE=1;
    override fun getItemViewType(position: Int): Int {
        val message = messagesList.get(position);
        if(message.senderId.equals(Data.user?.id)){
            return SENT_MESSAGE_TYPE;
        }
        return RECIEVED_MESSAGE_TYPE;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if(viewType==SENT_MESSAGE_TYPE){
            val viewDataBinding:LayoutMessageSentBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.layout_message_sent,parent,
                false)
            return SentMessageViewHolder(viewDataBinding)
        }

        val viewDataBinding:LayoutMessageRecievedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.layout_message_recieved,parent,
            false)
        return RecievedMessageViewHolder(viewDataBinding)


    }

    override fun getItemCount(): Int = messagesList.size


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message=messagesList.get(position);
//        val viewType = getItemViewType(position);
//        if(viewType==SENT_MESSAGE_TYPE){
//          holder as SentMessageViewHolder
//            holder.bind(message)
//        }else if(viewType==RECIEVED_MESSAGE_TYPE){
//            holder as RecievedMessageViewHolder
//            holder.bind(message)
//        }
        holder.bind(message)
    }

    fun addMessages(addedMessages: MutableList<Message>) {
        val oldMessagesCount = messagesList.size
        messagesList.addAll(addedMessages)
        notifyItemRangeInserted(oldMessagesCount,addedMessages.size)
    }
}
 abstract class MessageViewHolder(view:View):RecyclerView.ViewHolder(view){
    abstract fun bind(message:Message)
}
class SentMessageViewHolder(val binding:LayoutMessageSentBinding)
    :MessageViewHolder(binding.root){
    override fun bind(message: Message) {
        binding.setMessage(message)
//        binding.executePendingBindings()
        binding.invalidateAll()
    }

}
class RecievedMessageViewHolder(val binding:LayoutMessageRecievedBinding)
    :MessageViewHolder(binding.root){
    override fun bind(message: Message) {
        binding.setMessage(message)
        binding.executePendingBindings()
    }
}