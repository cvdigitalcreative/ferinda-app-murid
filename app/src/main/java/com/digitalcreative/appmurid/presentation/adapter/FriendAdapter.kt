package com.digitalcreative.appmurid.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Profile
import kotlinx.android.synthetic.main.item_person.view.*

class FriendAdapter : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    var friends = listOf<Profile.Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        )
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friends[position]
        holder.bind(friend)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(friend: Profile.Friend) {
            with(itemView) {
                tv_person_name.text = friend.name
                tv_person_id.text = friend.id
                tv_person_email.text = friend.email

                Glide.with(this)
                    .load(context.getString(R.string.ui_avatar, friend.name))
                    .into(img_person)
            }
        }
    }
}