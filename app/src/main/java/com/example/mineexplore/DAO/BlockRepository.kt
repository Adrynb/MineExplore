package com.example.mineexplore.DAO

import android.content.Context
import com.example.mineexplore.AppDataBase
import com.example.mineexplore.Block

class BlockRepository(var context: Context) {

    private  var _blockDao : BlockDao

    init {
        val dataBase = AppDataBase.getInstance(context)
        _blockDao = dataBase.blockDao()
    }

    fun insert(block : Block) : Long{
        return this._blockDao.insert(block)
    }

    fun delete(block: Block){
        this._blockDao.delete(block)
    }


    fun getAllBlocks() : MutableList<Block> {
        return _blockDao.getAllblocks().toMutableList()
    }

}