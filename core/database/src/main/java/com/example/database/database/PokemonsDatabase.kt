package com.example.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.PokemonDao
import com.example.database.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract fun pokemonsDao(): PokemonDao
}
