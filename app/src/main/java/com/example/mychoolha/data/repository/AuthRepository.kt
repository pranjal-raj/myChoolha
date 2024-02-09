package com.example.mychoolha.data.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser : FirebaseUser?
    suspend fun login(email : String , password : String) : Resources<FirebaseUser?>?
    suspend fun signup(name : String , email : String , password : String) : Resources<FirebaseUser?>?
    fun logout()
}