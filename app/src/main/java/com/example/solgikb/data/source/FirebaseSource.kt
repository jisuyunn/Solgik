package com.example.solgikb.data.source

import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.example.solgikb.utils.BOOK_REF
import com.example.solgikb.utils.CHECK_REF
import com.example.solgikb.utils.USER_REF
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseSource {

    private val db = Firebase.database
    private val userRef = db.getReference(USER_REF)
    private val bookRef = db.getReference(BOOK_REF)
    private val checkRef = db.getReference(CHECK_REF)

    suspend fun insertUser(user: User): Result<User> =
            suspendCoroutine { cont ->
                val key = userRef.push().key!!
                user.UId = key
                userRef.child(key).setValue(user) { error, ref ->
                    if(error == null) cont.resume(Result.Success(user))
                    else cont.resume(Result.Error(error.toException()))
                }
            }

    fun insertBook(book: Book) {
        val key = bookRef.push().key!!
        book.BId = key
        bookRef.child(key).setValue(book)
    }

    fun insertCheck(check: Check) {
        val key = checkRef.push().key!!
        check.CId = key
        checkRef.child(key).setValue(check)
    }

    suspend fun getUserById(id: String): Result<User> =
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

    suspend fun getUserByEmail(email: String): Result<User> =
            suspendCoroutine { cont ->
                val query = userRef.orderByChild("email").equalTo(email).limitToFirst(1)
                query.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChildren()) {
                            val data = snapshot.children.elementAt(0).getValue(User::class.java)
                            if(data != null) cont.resume(Result.Success(data))
                        } else cont.resume(Result.Error(KotlinNullPointerException()))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }

    suspend fun getCheckListByUId(id: String): Result<List<Check>> =
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

    suspend fun getBookListById(id: List<String>): Result<List<Book>> =
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

    suspend fun getBookListByUId(id: String): Result<List<Book>> =
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

    suspend fun getBookListByTitle(title: String): Result<List<Book>> =
            suspendCoroutine { cont ->
                bookRef.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val bookList = mutableListOf<Book>()
                        snapshot.children.forEach { data ->
                            val book = data.getValue(Book::class.java)
                            if(book != null) {
                                if(book.title.contains(title))
                                    bookList.add(book)
                            }
                        }
                        cont.resume(Result.Success(bookList))
                    }
                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(Result.Error(error.toException()))
                    }
                })
            }
}