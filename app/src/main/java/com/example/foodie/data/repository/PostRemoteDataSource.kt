package com.example.foodie.data.repository

import com.example.foodie.modal.Posts
import retrofit2.Response

interface PostRemoteDataSource {
    suspend fun getAllData(): Response<List<Posts>>

}
