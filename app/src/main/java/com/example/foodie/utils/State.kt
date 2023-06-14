package com.example.foodie.utils

sealed class State<T> {

    class Loading<T>():State<T>()
    data class Success<T>(val data:T):State<T>()
    data class Failure<T>(val message:String):State<T>()

    companion object{
        fun <T> loading()=Loading<T>()
        fun<T> success(data:T) = Success<T>(data)
        fun<T> failure(message: String)=Failure<T>(message)

         fun<T> fromresource(response:Response<T>) = when(response){
             is Response.Sucesss-> success(response.data)
             is Response.Failed-> failure(response.message)
         }
    }


}