package com.example.foodie.data.remote

import com.example.foodie.data.repository.PostRemoteDataSource
import com.example.foodie.modal.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostService:PostRemoteDataSource{

    @GET("api/posts")
    override suspend fun getAllData(): Response<List<Posts>>
}