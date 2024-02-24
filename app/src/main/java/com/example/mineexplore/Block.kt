package com.example.mineexplore

import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Block(
    @PrimaryKey
    var nombre: String,
    var imageURL: String?,
    var descripcion: String
)
