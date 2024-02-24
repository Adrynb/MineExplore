package com.example.mineexplore.DAO

import android.content.Context
import com.example.mineexplore.AppDataBase
import com.example.mineexplore.Block
import com.example.mineexplore.Items

class ItemsRepository (var context : Context) {

    private var itemsDao : ItemsDao

    init {
        val database = AppDataBase.getInstance(context)
        itemsDao = database.itemsDao()
    }

    fun insert(items: Items) : Long{
        return this.itemsDao.insert(items)
    }

    fun delete(items: Items){
        this.itemsDao.delete(items)
    }

    fun getAllItems() : MutableList<Items> {
        return itemsDao.getAllItems().toMutableList()
    }
}