package com.example.mineexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toolbar
import com.example.mineexplore.Fragments.LobbyFragment
import com.example.mineexplore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var imageView : ImageView
    private var imageViewClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById(R.id.imageViewMineExplore)
        imageView.setOnClickListener {
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
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun disableImageViewClick() {
        imageViewClickable = false
    }

    fun enableImageViewClick(){
        imageViewClickable = true
    }

}
