package com.example.mychoolha.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychoolha.data.repository.AuthRepository
import com.example.mychoolha.data.repository.AuthRepositoryImpl
import com.example.mychoolha.data.repository.Resources
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _loginFlow  = MutableStateFlow<Resources<FirebaseUser?>?>(null)
    var loginFlow : StateFlow<Resources<FirebaseUser?>?> = _loginFlow

    private var _signUpFlow  = MutableStateFlow<Resources<FirebaseUser?>?>(null)
    var signUpFlow : StateFlow<Resources<FirebaseUser?>?> = _signUpFlow



    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if(repository.currentUser != null){
           // _loginFlow.value = Resources.Success(repository.currentUser!!)
        }
    }

    fun login(email : String, password: String ) = viewModelScope.launch {
        _loginFlow.value =  Resources.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signUp(name : String, email : String, password: String ) =
        viewModelScope.launch {
            _signUpFlow.update {Resources.Loading}
           val result = repository.signup(name, email, password)
            _signUpFlow.update { result }

        }


    fun logout()
    {
        repository.logout()
        _signUpFlow.value =  null
        _loginFlow.value =  null
    }

    fun resetStates()
    {
        _loginFlow.update { null }
        _signUpFlow.update { null }
    }



}