package com.example.foodie.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vm:ViewModel,vb:ViewBinding> :AppCompatActivity(){

    abstract val postViewModel:vm
    protected lateinit var binding: vb
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState )
        binding=getViewBinding()
    }
    abstract fun getViewBinding(): vb
}