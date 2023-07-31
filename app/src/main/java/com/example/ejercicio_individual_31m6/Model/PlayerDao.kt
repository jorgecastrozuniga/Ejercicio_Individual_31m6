package com.example.ejercicio_individual_31m6.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ejercicio_individual_31m6.Model.Player


@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Query("SELECT * FROM tablaJugadores")
    fun showallplayers(): List<Player>

    //TODO revisar las funciones del DAO
}
