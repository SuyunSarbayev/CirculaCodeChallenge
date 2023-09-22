package com.example.impl.di

import android.content.Context
import android.content.SharedPreferences
import com.example.api.FeatureApiService
import com.example.api.repository.FeatureRepository
import com.example.impl.domain.PokemonUpdateUseCase
import com.example.impl.repository.FeatureRepositoryImpl
import com.example.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
interface FeatureModule {

    @Binds
    fun bindsFeatureRepository(featureRepositoryImpl: FeatureRepositoryImpl): FeatureRepository

    companion object {

        @Singleton
        @Provides
        fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun providesFeatureServiceApi(retrofit: Retrofit): FeatureApiService {
            return retrofit.create(FeatureApiService::class.java)
        }

        @Provides
        @Singleton
        fun providesPokemonUpdateUseCase(sharedPreferences: SharedPreferences): PokemonUpdateUseCase {
            return PokemonUpdateUseCase(sharedPreferences)
        }
    }
}
