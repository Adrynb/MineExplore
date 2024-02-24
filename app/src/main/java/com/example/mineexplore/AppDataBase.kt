package com.example.mineexplore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mineexplore.DAO.BlockDao
import com.example.mineexplore.DAO.ItemsDao
import com.example.mineexplore.DAO.MobDao

@Database(entities = [Mob::class, Block::class, Items::class], version = 3, exportSchema = false)


abstract class AppDataBase : RoomDatabase() {
    abstract fun mobDao(): MobDao
    abstract fun blockDao() : BlockDao
    abstract fun itemsDao() : ItemsDao

    companion object {
        private var instance: AppDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDataBase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDataBase::class.java,
                    "aplicacion_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build()

            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }
    }
}
