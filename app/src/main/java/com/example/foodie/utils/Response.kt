package com.example.foodie.utils

 sealed class Response<T>() {
     class Sucesss<T>(val data:T):Response<T>()
     class Failed<T>(val message:String):Response<T>()
}