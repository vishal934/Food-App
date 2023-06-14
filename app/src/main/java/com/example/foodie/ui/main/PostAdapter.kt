package com.example.foodie.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodie.databinding.LayoutPostBinding
import com.example.foodie.modal.Posts
import com.bumptech.glide.Glide




class PostAdapter   (private val itemClicked: (post: Posts) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Posts, PostAdapter.PostViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position).let { holder.bind(it, itemClicked) }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Posts>() {
            override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                return oldItem == newItem
            }

        }
    }

    class PostViewHolder(val binding: LayoutPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(posts: Posts, itemClicked: (post: Posts) -> Unit) {
            with(binding) {
                postTitle.text = posts.title
                postAuthor.text = posts.author
                Glide.with(root.context)
                    .load(posts.imageUrl).into(postImage)

                root.setOnClickListener {
                    itemClicked(posts)
                }
            }
        }


    }


}

