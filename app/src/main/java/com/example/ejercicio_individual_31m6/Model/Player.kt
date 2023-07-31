package com.example.ejercicio_individual_31m6.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tablaJugadores")
data class Player(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val idPlayer: Int=0,
    val apodo: String,
    val nombrecompleto: String,
    val edad: Int

)