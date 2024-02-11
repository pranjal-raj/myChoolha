package com.example.mychoolha.Presentation.screens.LoginSignupFlow


import android.content.Context
import android.opengl.Visibility
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mychoolha.data.repository.Resources
import com.example.mychoolha.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

lateinit var errorVisibility : MutableState<Boolean>
lateinit var error : MutableState<String>
var visibility = mutableStateOf(true)

//lateinit var signupflow : State<Resources<FirebaseUser?>?>

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavHostController) {
    var phoneNumber by remember {
        mutableStateOf("8299606580")
    }
    var fullName by remember {
        mutableStateOf("Pranjal")
    }
    var email by remember {
        mutableStateOf("prayank8c@gmail.com")
    }

    var password by remember {
        mutableStateOf("12345goo")
    }
    var password_duplicate by remember {
        mutableStateOf("12345goo")
    }

    var startCollecting = remember {
        mutableStateOf(false)
    }
    error = remember {
        mutableStateOf("")
    }

     errorVisibility = remember {
        mutableStateOf(false)
    }


    val signupflow = viewModel.signUpFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwener = LocalLifecycleOwner.current


    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        loadingcircle(visibility)
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Let's Begin Our Journey",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 40.sp
                )
                Text(text = "Create A New Account", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = fullName, onValueChange = { fullName = it },
                    placeholder = {
                        Text(
                            text = "Jon Snow",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Full Name") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = phoneNumber, onValueChange = { if(phoneNumber.length<10) phoneNumber = it },
                    placeholder = {
                        Text(
                            text = "XXX-XXX-XXXX",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Phone,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Mobile Number") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = email, onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = "jonsnow@example.cpm",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text(text = "Email Address") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = password, onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Enter Password",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text(text = "Password") },
                )

                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = password_duplicate, onValueChange = { password_duplicate = it  },
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )

                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        signUpChecker(phoneNumber,fullName,email,password,password_duplicate, viewModel).also{startCollecting.value=true}
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(ButtonDefaults.MinHeight.plus(5.dp))
                            .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 20.sp,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    //Icon(imageVector = SimpleIcons.Labview , contentDescription = null, tint = Color.Black,modifier = Modifier.scale(2f) )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already Have An Account?",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                    TextButton(onClick = { navController.popBackStack()}) {
                        Text(
                            text = "Login Here",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                errorChecker(errorVisibility)
            }


        }
    }


    lifecycleOwener.lifecycleScope.launch {
        lifecycleOwener.repeatOnLifecycle(Lifecycle.State.RESUMED)
        {
           // Log.d("fromRegisterScreen.kt", signupflow.value.toString())
            signupflow.value.let {
                if(startCollecting.value) {
                    when (it) {
                        is Resources.Failure -> {
                            startCollecting.value = false
                            Log.d("fromRegisterScreen.kt", "Failure")
                            Toast.makeText(
                                context,
                                "Error : ${it.exception.message} ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Resources.Loading -> {
                            Log.d("fromRegisterScreen.kt", "LOADING...")




                        }

                        is Resources.Success -> {
                            startCollecting.value = false
                            Log.d("fromRegisterScreen.kt", "Success")
                            navController.apply {
                                popBackStack()
                                popBackStack()
                                navigate("home")
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}




fun isEmailValid(email: String): Boolean {
        return email.contains('@')
    }

    fun signUpChecker(phoneNumber:String,fullName:String,email:String,password:String,password_duplicate:String, viewModel: AuthViewModel) {
        if (phoneNumber.isNotEmpty() && fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password_duplicate.isNotEmpty()) {
            if (phoneNumber.length == 10) {
                if (isEmailValid(email)) {
                    if (password == password_duplicate) {
                        errorVisibility.value = false
                        viewModel.signUp(fullName, email, password)
                        //Log.d("jhoom", "ho gya")
                    }
                    else {
                        errorVisibility.value = true
                        error.value = "Passwords Mismatch"
                    }
                }
                else {
                    errorVisibility.value = true
                    error.value = "Email Invalid"
                }
            }
            else {
                errorVisibility.value = true
                error.value = "Mobile Number Invalid"
            }
        }
        else {
            errorVisibility.value = true
            error.value = "Please Fill all Details"
            Log.d("jhoom", "ho gya  $errorVisibility")
        }
    }



















    @Composable
    fun errorChecker(visibility: MutableState<Boolean>) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .alpha(if (visibility.value) 1f else 0f)
                    .background(Color.Red)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                    , modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Close,
                        contentDescription = "worng",
                        tint = Color.White
                    )
                    Text(
                        text = error.value,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
            }

        }


@Composable
fun loadingcircle(visibility: MutableState<Boolean>)
{
    if(visibility.value) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(180.dp)
                .size(50.dp)
        )
    }
}



