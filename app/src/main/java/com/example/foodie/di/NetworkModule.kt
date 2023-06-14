package com.example.foodie.di

import com.example.foodie.data.remote.PostService
import com.example.foodie.data.repository.PostRemoteDataSource
import com.example.foodie.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule{

    @Provides
    @Singleton
     fun provideRetrofitService(): Retrofit {
         return  Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create()).build()
     }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit):PostRemoteDataSource{
        return retrofit.create(PostService::class.java)
    }

 }
