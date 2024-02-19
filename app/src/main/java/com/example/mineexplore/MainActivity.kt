package com.example.mineexplore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mineexplore.Fragments.BlockFragment
import com.example.mineexplore.Fragments.ItemFragment
import com.example.mineexplore.Fragments.LobbyFragment
import com.example.mineexplore.Fragments.MobFragment
import com.example.mineexplore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageViewClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        

        binding.imageViewMineExplore.setOnClickListener {
            if (imageViewClickable) {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, LobbyFragment())
                    addToBackStack("replacement")
                    commit()
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
            com.example.mineexplore.R.id.blockMenuItem -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, BlockFragment())
                    addToBackStack("replacement")
                    commit()
                }
                true
            }

            com.example.mineexplore.R.id.itemMenuItem -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, ItemFragment())
                    addToBackStack("replacement")
                    commit()
                }
                true
            }

            com.example.mineexplore.R.id.mobMenuItem -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, MobFragment())
                    addToBackStack("replacement")
                    commit()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun disableImageViewClick() {
        imageViewClickable = false
    }

    fun enableImageViewClick() {
        imageViewClickable = true
    }
}
