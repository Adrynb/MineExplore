package com.example.mineexplore

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    @PrimaryKey
    var nombre: String,
    var imageURL : String?,
    var descripcion: String)
