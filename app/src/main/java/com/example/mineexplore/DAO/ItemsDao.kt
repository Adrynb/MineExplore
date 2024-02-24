package com.example.mineexplore.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mineexplore.Items

@Dao
interface ItemsDao {

    @Insert
    fun insert(item : Items) : Long

    @Delete
    fun delete(Item : Items)
    @Query("SELECT * FROM items")
    fun getAllItems() : List<Items>
}