package com.example.mineexplore

import BlockViewModel
import GatoServicio
import MobViewModel
import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.example.mineexplore.Fragments.BlockFragment
import com.example.mineexplore.Fragments.ItemFragment
import com.example.mineexplore.Fragments.LobbyFragment
import com.example.mineexplore.Fragments.MobFragment
import com.example.mineexplore.RetroFit.RetroFit
import com.example.mineexplore.ViewModels.ItemViewModel
import com.example.mineexplore.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


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
    private val  mobViewModel : MobViewModel by viewModels()
    private val blockViewModel : BlockViewModel by viewModels()
    private val itemViewModel : ItemViewModel by viewModels()
    private var musicJob: Job = Job()
    private lateinit var mediaPlayer : MediaPlayer
    private var  isMusicPlaying = false
    private lateinit var gatoServicio : GatoServicio


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gatoServicio = RetroFit.instance.create(GatoServicio::class.java)

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

        binding.imageViewMineExplore.setOnClickListener {//Darle click para remplazar fragmento.
            if (imageViewClickable) {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, LobbyFragment())
                }
            }
        }
        //Inicializar los viewmodels para la base de datos ROOM (pasandole el contexto de la actividad)

        this.mobViewModel.initialize(this)
        this.blockViewModel.initialize(this)
        this.itemViewModel.initialize(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuprincipal, menu)
        return true
    }

    /*
    Menu de items para cambiar de fragmentos. Cada uno lleva una nueva instancia.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.blockMenuItem -> {
                replaceFragment(BlockFragment())
                true
            }

            R.id.itemMenuItem -> {
                replaceFragment(ItemFragment())
                true
            }

            R.id.mobMenuItem -> {
                replaceFragment(MobFragment())
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
                showDialog("Derechos de Autor", " © 2024 Adrián Navarro Buceta. Todos los derechos robados para un buen uso.")
                return true
            }
            R.id.menu_aboutme -> {
                showDialog("Acerca de Mí", "¡Hola! Soy Adrián y esta es mi aplicación de minexplore. Cumple los requisitos mínimos para aprobar (o eso creo), gracias.")
                return true
            }

            R.id.menu_musica -> {

                if(isMusicPlaying){
                    stopMusic()
                }
                else{
                    startMusic()
                }
                return true
            }

            R.id.menu_gato -> {
                getRandomCatImage()
                return true

            }

            else -> return false
        }
    }


    /*
    Funcion que remplaza los fragmentos, a través del fragment_container.
     */
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
        }
    }

    /*
    Función que demuestra un dialogo de texto.
     */
    fun showDialog(title: String, message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
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

    private fun startMusic() {
        if (!musicJob.isActive || !isMusicPlaying) {
            musicJob = CoroutineScope(Dispatchers.Main).launch {
                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.minecraft)
                mediaPlayer.isLooping = true
                mediaPlayer.start()
                isMusicPlaying = true
            }
        }
    }
    private fun stopMusic() {
        musicJob.cancel()
        mediaPlayer.release()
        isMusicPlaying = false
    }

    private fun getRandomCatImage() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val imagenesGatos = gatoServicio.getRandomCatImage()
                if (imagenesGatos.isNotEmpty()) {
                    val randomCatImage = imagenesGatos.random()
                    Glide.with(this@MainActivity).load(randomCatImage.url).into(headerImageView)
                }
                else{
                    Log.d("ErrorImage", "La lista esta vacía :c")
                }
            } catch (e: Exception) {
                e.printStackTrace()
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
