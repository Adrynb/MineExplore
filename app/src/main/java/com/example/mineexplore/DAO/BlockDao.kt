package com.example.mineexplore.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mineexplore.Block

@Dao
interface BlockDao {

    @Insert
    fun insert(block : Block) : Long

    @Delete
    fun delete(block : Block)

    @Query("SELECT * FROM block")
    fun getAllblocks() : List<Block>
}