package com.example.solgikb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.solgikb.R
import com.example.solgikb.databinding.ActivityMainBinding

class BookInfoActivity : AppCompatActivity() {

    private lateinit var bingding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_book_info)
    }

}