package com.example.mychoolha.data.repository

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resources<FirebaseUser?> {
       return try {
           val resut  = firebaseAuth.signInWithEmailAndPassword(email, password).await().user
           Resources.Success(resut)
       }
       catch(e : Exception)
       {
           e.printStackTrace()
           Resources.Failure(e)
        }
    }

    override suspend fun signup(name :String , email: String, password: String): Resources<FirebaseUser?> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
            Resources.Success(result.user)
        }
        catch(e : Exception)
        {
            e.printStackTrace()
            Resources.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}