package com.example.foodie.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.databinding.ActivityPostDetailBinding
import com.example.foodie.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetail : BaseActivity<PostDetailViewModel,ActivityPostDetailBinding>(){

    override val postViewModel: PostDetailViewModel by viewModels ()


    override fun getViewBinding(): ActivityPostDetailBinding = ActivityPostDetailBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        intent.extras?.getInt(KEY_INTENT).apply {
            this?.let { postViewModel.getPostDetails(it).observe(this@PostDetail, Observer {post->
                binding.apply {
                    postContent.apply {
                        Log.d("TAG", "initUIpost.body: ${post.body} ")
                        postTitle.text=post.title
                        postAuthor.text=post.author
                        postBody.text=post.body
                    }

                    Glide.with(this@PostDetail).load(post.imageUrl).into(image)
                }

            }) }
        }
    }

companion object{
    const val  KEY_INTENT ="post_id"
    fun  getIntent(context: Context, posId:Int)=
        Intent(context,PostDetail::class.java).apply {
            putExtra(KEY_INTENT,posId)
        }
}


}