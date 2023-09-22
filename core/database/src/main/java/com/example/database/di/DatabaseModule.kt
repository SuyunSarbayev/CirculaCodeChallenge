package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.dao.PokemonDao
import com.example.database.database.PokemonsDatabase
import com.example.database.repository.PokemonsDatabaseRepository
import com.example.database.repository.PokemonsDatabaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    @Binds
    fun bindPokemonsRepository(pokemonsDatabaseRepositoryImpl: PokemonsDatabaseRepositoryImpl): PokemonsDatabaseRepository

    companion object {

        @Provides
        fun providePokemonsDao(pokemonsDatabase: PokemonsDatabase): PokemonDao {
            return pokemonsDatabase.pokemonsDao()
        }

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext applicationContext: Context): PokemonsDatabase {
            return Room.databaseBuilder(
                applicationContext,
                PokemonsDatabase::class.java,
                "pokemons-database",
            ).build()
        }
    }
}
