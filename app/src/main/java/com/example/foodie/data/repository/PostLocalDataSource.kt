package com.example.foodie.data.repository

import com.example.foodie.modal.Posts
import kotlinx.coroutines.flow.Flow

interface PostLocalDataSource {
    suspend fun addPosts(postlist:List<Posts>)
    fun getAllPosts(): Flow<List<Posts>>
    suspend fun deletePost()
    fun getPostByID(id:Int):Flow<Posts>

}