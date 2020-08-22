package com.digitalcreative.appmurid.presentation.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.FriendAdapter
import com.digitalcreative.appmurid.data.model.Profile
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_person.view.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(this)
            .load("https://m.media-amazon.com/images/M/MV5BMTkxMzk2MDkwOV5BMl5BanBnXkFtZTcwMDAxODQwMg@@._V1_UX178_CR0,0,178,264_AL_.jpg")
            .into(img_profile)

        tv_profile_name.text = "Jason Statham"
        tv_profile_id.text = "1234567890"

        layout_teacher.apply {
            tv_person_name.text = "Teacher"
            tv_person_id.text = "0987654321"
            tv_person_email.text = "email@teacher.com"

            Glide.with(this)
                .load("https://media.sciencephoto.com/f0/13/84/48/f0138448-400px-wm.jpg")
                .into(img_person)
        }

        val listFriend = listOf(
            Profile(
                "1234567890",
                "Profile 1",
                "email1@mail.com",
                "https://news.ucsc.edu/2017/02/images/mcc-student-400px.jpg"
            ),
            Profile(
                "1234567890",
                "Profile 1",
                "email1@mail.com",
                "https://news.ucsc.edu/2017/02/images/mcc-student-400px.jpg"
            ),
            Profile(
                "1234567890",
                "Profile 1",
                "email1@mail.com",
                "https://news.ucsc.edu/2017/02/images/mcc-student-400px.jpg"
            ),
            Profile(
                "1234567890",
                "Profile 1",
                "email1@mail.com",
                "https://news.ucsc.edu/2017/02/images/mcc-student-400px.jpg"
            )
        )
        val friendAdapter = FriendAdapter()
        friendAdapter.friends = listFriend

        rv_friends.apply {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}