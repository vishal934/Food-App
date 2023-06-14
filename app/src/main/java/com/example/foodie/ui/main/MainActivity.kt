package com.example.foodie.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.R
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.modal.Posts
import com.example.foodie.ui.base.BaseActivity
import com.example.foodie.ui.detail.PostDetail
import com.example.foodie.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<PostViewModel, ActivityMainBinding>() {

    override val postViewModel: PostViewModel by viewModels()
    private val postAdapter = PostAdapter(::itemClicked)
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
      getPosts()
        observePosts()

    }

    override fun onStart() {
        super.onStart()
//        handleNetworkChanges()
    }

    private fun getPosts() = postViewModel.getPosts()
    private fun observePosts() {
        lifecycleScope.launch {
            postViewModel.posts.collect { state ->
                when (state) {
                    is State.Loading -> {
                        showLoading(true)
                    }
                    is State.Success -> {
                        postAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                    is State.Failure -> {
//                        showToast(state.message)
                        showLoading(false)
                    }
                    else -> {
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(isRefreshing: Boolean) {
        binding.swipeRefresh.isRefreshing = isRefreshing
    }

    private fun initUI() {
        binding.apply {
            recyclerView.adapter = postAdapter
            swipeRefresh.setOnRefreshListener { getPosts() }
        }
    }

    private fun handleNetworkChanges() {

    }

    companion object {
        const val ANIMATION_DURATION = 1000L
    }

    private fun itemClicked(post: Posts) {
        var postId = post.id ?: run {
//            showToast("No ID Found")
            return
        }

        val intent= PostDetail.getIntent(this,postId)
        startActivity(intent)

    }
}