package com.example.foodie.data.repository

import com.example.foodie.modal.Posts
import com.example.foodie.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    val localDS:PostLocalDataSource,
    val remoteDS:PostRemoteDataSource
    ){
    fun getAllPosts(): Flow<Response<List<Posts>>>{
        return object :NetworkBoundRepository<List<Posts>,List<Posts>>(){
            override suspend fun saveToLocal(postList: List<Posts>) =localDS.addPosts(postList)

            override fun fetchfromLocal(): Flow<List<Posts>> =  localDS.getAllPosts()

            override suspend fun fetchFromAPI(): retrofit2.Response<List<Posts>> =remoteDS.getAllData()

        }.onFlow()
    }

 fun getPostByID(id:Int):Flow<Posts> = localDS.getPostByID(id)

}