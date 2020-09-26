package com.digitalcreative.appmurid.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.presentation.ui.home.agenda.AgendaFragment
import com.digitalcreative.appmurid.presentation.ui.home.assignment.AssignmentFragment
import com.digitalcreative.appmurid.presentation.ui.home.profile.ProfileFragment
import com.digitalcreative.appmurid.presentation.ui.home.report.RaportFragment
import com.digitalcreative.appmurid.presentation.ui.home.school.SchoolFragment
import com.digitalcreative.appmurid.presentation.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            title = getString(R.string.app_name)
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_tugas -> {
                    loadFragment(AssignmentFragment())
                }
                R.id.menu_raport -> {
                    loadFragment(RaportFragment())
                }
                R.id.menu_agenda -> {
                    loadFragment(AgendaFragment())
                }
                R.id.menu_sekolah -> {
                    loadFragment(SchoolFragment())
                }
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.menu_tugas
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            showLogoutConfirmation()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun showLogoutConfirmation() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle(R.string.logout)
            setMessage(R.string.logout_message)

            setPositiveButton(R.string.ya) { _, _ ->
                viewModel.logout()
                showLoginActivity()
            }

            setNegativeButton(R.string.tidak) { _, _ -> }
        }.show()
    }

    private fun showLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}