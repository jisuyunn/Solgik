package com.example.solgikb.data.repository

import com.example.solgikb.data.DataManager
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.example.solgikb.utils.BOOK_REF
import com.example.solgikb.utils.CHECK_REF
import com.example.solgikb.utils.USER_REF
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.NullPointerException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRepository: DataManager {

    private val db = Firebase.database
    private val userRef = db.getReference(USER_REF)
    private val bookRef = db.getReference(BOOK_REF)
    private val checkRef = db.getReference(CHECK_REF)

    override fun insertUser(user: User) {
        val key = userRef.push().key!!
        user.UId = key
        userRef.child(key).setValue(user)
        //콜백생략
    }

    override fun insertBook(book: Book) {
        val key = bookRef.push().key!!
        book.BId = key
        bookRef.child(key).setValue(book)
    }

    override fun insertCheck(check: Check) {
        val key = checkRef.push().key!!
        check.CId = key
        checkRef.child(key).setValue(check)
    }

    override suspend fun getUserById(id: String): Result<User> =
            suspendCoroutine { cont ->
                val query = userRef.orderByKey().equalTo(id).limitToFirst(1)
                query.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChildren()) {
                            val data = snapshot.children.elementAt(0).getValue(User::class.java)
                            if(data != null) cont.resume(Result.Success(data))
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }

    override suspend fun getCheckListByUId(id: String): Result<List<Check>> =
            suspendCoroutine { cont ->
                val query = checkRef.orderByChild("uid").equalTo(id)
                query.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val checkList = mutableListOf<Check>()
                        snapshot.children.forEach { data ->
                            val check = data.getValue(Check::class.java)
                            if(check != null && check.returnDate.equals("")) checkList.add(check)
                        }
                        cont.resume(Result.Success(checkList))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }

    override suspend fun getBookListById(id: List<String>): Result<List<Book>> =
            suspendCoroutine { cont ->
                bookRef.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val bookList = mutableListOf<Book>()
                        id.forEach { id ->
                            val data = snapshot.child(id).getValue(Book::class.java)
                            if(data != null) bookList.add(data)
                        }
                        cont.resume(Result.Success(bookList))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }

    override suspend fun getBookListByUId(id: String): Result<List<Book>> =
            suspendCoroutine { cont ->
                bookRef.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val bookList = mutableListOf<Book>()
                        snapshot.children.forEach { data ->
                            val book = data.getValue(Book::class.java)
                            if(book != null) bookList.add(book)
                        }
                        cont.resume(Result.Success(bookList))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }
}