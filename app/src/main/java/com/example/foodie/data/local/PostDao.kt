package com.example.foodie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodie.data.repository.PostLocalDataSource
import com.example.foodie.modal.Posts
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao:PostLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addPosts(postList: List<Posts>)

    @Query("select * from posts")
    override fun getAllPosts(): Flow<List<Posts>>

    @Query("delete from posts")
    override suspend fun deletePost()

    @Query("select * from posts  where id=:id")
    override  fun getPostByID(id: Int): Flow<Posts>

}