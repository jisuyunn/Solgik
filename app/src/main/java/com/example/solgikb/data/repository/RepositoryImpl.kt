package com.example.solgikb.data.repository

import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.example.solgikb.data.source.FirebaseSource
import com.example.solgikb.data.source.PreferenceSource
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl(val db: FirebaseSource, val pref: PreferenceSource): IRepository {

    override fun insertBook(book: Book) {
        db.insertBook(book)
    }

    override fun insertCheck(check: Check) {
        db.insertCheck(check)
    }

    override suspend fun getUserById(id: String): Result<User> = db.getUserById(id)

    override suspend fun getUserByEmail(email: String): Result<User> = getUserByEmail(email)

    override suspend fun getCheckListByUId(id: String): Result<List<Check>> = db.getCheckListByUId(id)

    override suspend fun getBookListById(id: List<String>): Result<List<Book>> = db.getBookListById(id)

    override suspend fun getBookListByUId(id: String): Result<List<Book>> = db.getBookListByUId(id)

    override suspend fun getBookListByTitle(title: String): Result<List<Book>> = db.getBookListByTitle(title)

    override fun getUser(): User = pref.getUser()

    override fun saveUser(user: User) {
        pref.saveUser(user)
    }

    override suspend fun checkUserAndLogin(user: User): Result<User> {
        var result = db.getUserByEmail(user.email)
        if (result is Result.Error) result = db.insertUser(user)
        return suspendCoroutine { cont ->
            if(result is Result.Success) pref.saveUser(result.data)
            cont.resume(result)
        }
    }

}