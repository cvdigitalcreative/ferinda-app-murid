package com.digitalcreative.appmurid.presentation.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Profile
import com.digitalcreative.appmurid.presentation.adapter.FriendAdapter
import com.digitalcreative.appmurid.utils.helper.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_person.view.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel>()
    private val loadingDialog by loadingDialog()
    private val friendAdapter = FriendAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()

        rv_friends.apply {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, Observer(this::showLoading))
        viewModel.profile.observe(viewLifecycleOwner, Observer(this::showProfile))
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer(this::showMessage))
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

    private fun showProfile(profile: Profile) {
        Glide.with(this)
            .load(getString(R.string.ui_avatar, profile.name))
            .into(img_profile)

        tv_profile_name.text = profile.name
        tv_profile_id.text = profile.id

        layout_teacher.apply {
            tv_person_name.text = profile.teacherName
            tv_person_id.text = profile.teacherId
            tv_person_email.text = profile.teacherEmail

            Glide.with(this)
                .load(getString(R.string.ui_avatar, profile.teacherName))
                .into(img_person)
        }

        friendAdapter.apply {
            friends = profile.friends
            notifyDataSetChanged()
        }
    }

    private fun showMessage(message: String) {
        Toasty.error(requireContext(), message, Toasty.LENGTH_LONG, true).show()
    }
}