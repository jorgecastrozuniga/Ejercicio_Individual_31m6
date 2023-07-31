package com.example.ejercicio_individual_31m6.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDataBase: RoomDatabase () {

    abstract fun getPlayerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerDataBase? = null

        fun getDataBase(context: Context): PlayerDataBase {
            /*if (INSTANCE == null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerDataBase::class.java,
                        "jugadoresDB"
                    ).build()
                }
            }

            return INSTANCE!!
            */

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDataBase::class.java,
                    "jugadoresDB"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}