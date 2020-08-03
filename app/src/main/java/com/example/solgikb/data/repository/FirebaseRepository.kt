package com.example.solgikb.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.solgikb.data.DataManager
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRepository: DataManager {

    private val db = Firebase.database
    private val userRef = db.getReference("User")
    private val bookRef = db.getReference("Book")
    private val checkRef = db.getReference("Check")

    override fun insertUser(user: User) {
        val key = userRef.push().key!!
        user.UId = key
        userRef.child(key).setValue(user)
    }


}