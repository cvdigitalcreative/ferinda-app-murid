package com.digitalcreative.appmurid.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.ui.home.agenda.AgendaFragment
import com.digitalcreative.appmurid.ui.home.profile.ProfileFragment
import com.digitalcreative.appmurid.ui.home.raport.RaportFragment
import com.digitalcreative.appmurid.ui.home.tugas.TugasFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_tugas -> {
                    loadFragment(TugasFragment())
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