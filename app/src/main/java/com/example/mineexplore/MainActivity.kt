package com.example.mineexplore

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
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
    private lateinit var requestCamera: ActivityResultLauncher<Void?>
    val REQUEST_IMAGE_CAPTURE = 1001
    private lateinit var headerImageView: ImageView
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        setSupportActionBar(binding.toolbar)
        val headerView = navigationView.getHeaderView(0)
        headerImageView = headerView.findViewById(R.id.headerImage)

        navigationView.setNavigationItemSelectedListener(this)


        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        requestConfigs()

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
                if (ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        REQUEST_IMAGE_CAPTURE
                    )
                } else {
                    openCamera()
                }

                return true
            }
            R.id.menu_copyright -> {
                showCopyrightDialog()
                return true
            }
            R.id.menu_aboutme -> {
                showAboutMeDialog()
                return true
            }
            else -> return false
        }
    }

    private fun showAboutMeDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Acerca de Mí")
        dialogBuilder.setMessage("¡Hola! Soy Adrián y esta es mi aplicación de minexplore. Cumple los requisitos minimos para aprobar (o eso creo), gracias.")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun showCopyrightDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Derechos de Autor")
        dialogBuilder.setMessage(" © 2024 Adrián Navarro Buceta. Todos los derechos robados para un buen uso.")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }


    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            requestCamera.launch(null)
        } else {
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.CAMERA))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(
                    this,
                    "El permiso para acceder a la camara es denegado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun requestConfigs(){

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }

        requestCamera = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) {
            it?.let { bitmap ->
                headerImageView.setImageBitmap(bitmap)
            }
        }
    }

    fun disableImageViewClick() {
        imageViewClickable = false
    }

    fun enableImageViewClick() {
        imageViewClickable = true
    }




}
