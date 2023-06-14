package com.example.foodie.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.modal.Posts

class MainActivity2 : AppCompatActivity() {

    lateinit var rvShow: RecyclerView
    var postList = ArrayList<Posts>()
    lateinit var postAdapter: PostAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        rvShow = findViewById(R.id.rvShow)
        postList.add(Posts("AA", "SSS", 4, "llllllllllll", "llllllllllllllllllllllll"))



    }
}