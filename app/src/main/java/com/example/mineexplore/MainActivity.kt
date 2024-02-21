package com.example.mineexplore

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
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




    fun disableImageViewClick() {
        imageViewClickable = false
    }

    fun enableImageViewClick() {
        imageViewClickable = true
    }
}
