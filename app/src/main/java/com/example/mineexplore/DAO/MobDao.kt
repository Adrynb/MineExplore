package com.example.mineexplore.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mineexplore.Mob

@Dao
interface MobDao {

    @Insert
    fun insert(mob : Mob) : Long

    @Delete
    fun delete(mob : Mob)

    @Update
    fun update(mob : Mob)

    @Query("SELECT * FROM mob")
    fun getAllMobs() : List<Mob>
}