package com.example.foodie.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.foodie.data.local.FoodiumPostsDatabase
import com.example.foodie.data.repository.PostLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context):FoodiumPostsDatabase{
        return Room.databaseBuilder(context,FoodiumPostsDatabase::class.java,"POSTDetails").build()
    }
    @Provides
    @Singleton
    fun providePostDao(postDatabase:FoodiumPostsDatabase):PostLocalDataSource{
        return postDatabase.getPostsDao()
    }
}