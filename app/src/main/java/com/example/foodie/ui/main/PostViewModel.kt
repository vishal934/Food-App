package com.example.foodie.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.repository.PostRepository
import com.example.foodie.modal.Posts
import com.example.foodie.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(val postRepository: PostRepository):ViewModel(){

    private val _posts:MutableStateFlow<State<List<Posts>>> = MutableStateFlow(State.loading())
    val posts : StateFlow<State<List<Posts>>> = _posts

    fun getPosts(){
        viewModelScope.launch {
            postRepository.getAllPosts().map { response->


                State.fromresource(response)
            }.collect{
                Log.d("Foodie",it.toString())
                _posts.value=it
            }
        }
    }
}