package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("pokemonId") val pokemonId: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("imagePath") val imagePath: String,
)
