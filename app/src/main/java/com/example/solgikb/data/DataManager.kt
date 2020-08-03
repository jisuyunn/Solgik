package com.example.solgikb.data

import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User


interface DataManager {

    fun insertUser(user: User)
}