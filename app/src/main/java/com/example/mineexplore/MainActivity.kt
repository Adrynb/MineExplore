package com.example.mineexplore

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.example.mineexplore.Fragments.BlockFragment
import com.example.mineexplore.Fragments.ItemFragment
import com.example.mineexplore.Fragments.LobbyFragment
import com.example.mineexplore.Fragments.MobFragment
import com.example.mineexplore.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var imageViewClickable = true
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        setSupportActionBar(binding.toolbar)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        binding.imageViewMineExplore.setOnClickListener {
            if (imageViewClickable) {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, LobbyFragment())
                    addToBackStack("replacement")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuprincipal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.blockMenuItem -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, BlockFragment())
                    addToBackStack("replacement")
                }
                true
            }
            R.id.itemMenuItem -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, ItemFragment())
                    addToBackStack("replacement")
                }
                true
            }
            R.id.mobMenuItem -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, MobFragment())
                    addToBackStack("replacement")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_change_photo -> {

            }
            R.id.menu_aboutme -> {

            }
            R.id.menu_copyright -> {

            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun disableImageViewClick() {
        imageViewClickable = false
    }

    fun enableImageViewClick() {
        imageViewClickable = true
    }
}
