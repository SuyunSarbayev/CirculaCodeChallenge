package com.example.database.repository

import androidx.room.withTransaction
import com.example.database.database.PokemonsDatabase
import com.example.database.dao.PokemonDao
import com.example.database.entity.PokemonEntity
import javax.inject.Inject

class PokemonsDatabaseRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonsDatabase: PokemonsDatabase,
) : PokemonsDatabaseRepository {
    override suspend fun initiateInsertPokemons(pokemonsEntity: List<PokemonEntity>) {
        pokemonDao.initiateInsertPokemons(pokemonsEntity)
    }

    override suspend fun initiateInsertPokemon(pokemonEntity: PokemonEntity) {
        pokemonsDatabase.withTransaction {
            pokemonDao.initiateInsertPokemon(pokemonEntity)
        }
    }

    override suspend fun initiateRequestPokemons(): List<PokemonEntity> {
        return pokemonsDatabase.withTransaction {
            pokemonDao.initiateGetPokemons()
        }
    }

    override suspend fun initiateRequestPokemon(pokemonId: String): PokemonEntity {
        return pokemonsDatabase.withTransaction {
            pokemonDao.initiateGetPokemonsById(pokemonId)
        }
    }

    override suspend fun initiateClearPokemons() {
        pokemonsDatabase.withTransaction {
            pokemonDao.initiateClearPokemons()
        }
    }
}
