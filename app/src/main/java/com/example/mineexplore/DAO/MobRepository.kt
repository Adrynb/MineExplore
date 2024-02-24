package com.example.mineexplore.DAO

import android.content.Context
import com.example.mineexplore.AppDataBase
import com.example.mineexplore.Mob

class MobRepository(var context : Context) {

    private  var _mobDao : MobDao

    init {
        val database = AppDataBase.getInstance(context)
        _mobDao = database.mobDao()
    }


    fun insert(mob : Mob) : Long{
        return this._mobDao.insert(mob)
    }

    fun delete(mob : Mob){
        this._mobDao.delete(mob)
    }


    fun getAllMobs() : MutableList<Mob> {
        return _mobDao.getAllMobs().toMutableList()
    }
}