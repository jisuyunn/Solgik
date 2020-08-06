package com.example.solgikb.ui.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.solgikb.R
import com.example.solgikb.databinding.ActivityMainBinding

class BookDetailActivity : AppCompatActivity()  {

    private lateinit var bingding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_book_info)
    }

}