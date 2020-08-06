package com.example.solgikb.ui.login

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.solgikb.data.model.User
import com.example.solgikb.data.repository.IRepository
import com.example.solgikb.ui.base.BaseViewModel
import com.example.solgikb.utils.GOOGLE_CLIENT_ID
import com.example.solgikb.utils.REQUEST_CODE_GOOGLE_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginViewModel(val application: Application, private val repo: IRepository): BaseViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData = _userLiveData.switchMap { user ->
        liveData {
            emit(repo.checkUserAndLogin(user))
        }
    }

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

    private val mGoogleSignClient by lazy {
        GoogleSignIn.getClient(application, gso)
    }

    fun googleSignIn() = mGoogleSignClient.signInIntent

    fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN && data != null) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null) firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                Log.d(TAG, "Google sign in failed", e)
            }
        }
    }

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User("",auth.currentUser?.email.toString(),auth.currentUser?.displayName.toString(),"","","대출 가능","")
                        _userLiveData.value = user
                    }
                }
    }
}