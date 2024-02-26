package com.example.mineexplore
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Mob(
    @PrimaryKey
    var nombre: String,
    var imageURL : String?,
    var descripcion: String)
