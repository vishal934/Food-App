package com.example.foodie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.foodie.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(private val postRepository: PostRepository):ViewModel() {

    fun getPostDetails(id:Int) = postRepository.getPostByID(id).asLiveData()
}