package com.example.foodie.data.repository

import android.util.Log
import com.example.foodie.utils.Response
import kotlinx.coroutines.flow.*

abstract class NetworkBoundRepository<Request,Result> {

    fun onFlow()= flow {
        emit(Response.Sucesss(fetchfromLocal().first()))
        val apiResponse = fetchFromAPI()
        val remotePost = apiResponse.body()
        if (apiResponse.isSuccessful && remotePost != null)
        {
            saveToLocal(remotePost)
        }
        else{
            emit(Response.Failed(apiResponse.message()))
        }
        emitAll(fetchfromLocal().map {
            Log.d("TAG", "fetchfromLocal:  $it")
            Response.Sucesss(it)
        })
    }.catch {e ->
        emit(Response.Failed(e.localizedMessage))
    }

    abstract suspend fun saveToLocal(postList:Request)
    abstract fun fetchfromLocal():Flow<Result>
    abstract suspend fun fetchFromAPI():retrofit2.Response<Request>
}