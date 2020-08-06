package com.example.solgikb.data.repository

import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.google.firebase.database.Query


interface IRepository {
    fun insertBook(book: Book)
    fun insertCheck(check: Check)

    suspend fun getUserById(id: String): Result<User>
    suspend fun getUserByEmail(email: String): Result<User>
    suspend fun getCheckListByUId(id: String): Result<List<Check>>
    suspend fun getBookListById(id: List<String>): Result<List<Book>>
    suspend fun getBookListByUId(id: String): Result<List<Book>>

    fun getUser(): User
    fun saveUser(user: User)

    suspend fun checkUserAndLogin(user: User): Result<User>
}