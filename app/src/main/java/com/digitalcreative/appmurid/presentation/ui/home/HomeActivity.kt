package com.digitalcreative.appmurid.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.presentation.ui.home.agenda.AgendaFragment
import com.digitalcreative.appmurid.presentation.ui.home.assignment.AssignmentFragment
import com.digitalcreative.appmurid.presentation.ui.home.profile.ProfileFragment
import com.digitalcreative.appmurid.presentation.ui.home.report.RaportFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.menu_tugas
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}