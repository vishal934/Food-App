package com.example.foodie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.modal.Posts

@Database(entities = [Posts::class], version = 1)
abstract class FoodiumPostsDatabase :RoomDatabase(){

abstract fun getPostsDao(): PostDao

}