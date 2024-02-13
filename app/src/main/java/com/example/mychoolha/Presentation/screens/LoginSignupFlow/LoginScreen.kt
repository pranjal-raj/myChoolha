package com.example.mychoolha.Presentation.screens.LoginSignupFlow


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mychoolha.Presentation.navigation.myNavGraph
import com.example.mychoolha.Presentation.screens.LoginSignupFlow.util.errorChecker
import com.example.mychoolha.R
import com.example.mychoolha.data.repository.Resources
import com.example.mychoolha.ui.theme.LightYellow
import com.example.mychoolha.util.Lato
import com.example.mychoolha.util.Montserrat
import com.example.mychoolha.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.SimpleIcons

import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Hospital
import compose.icons.simpleicons.Labview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var circleVisibility = mutableStateOf(false)
private lateinit var errorVisibility : MutableState<Boolean>
private lateinit var error : MutableState<String>

@Composable
fun LoginScreen(viewModel : AuthViewModel, navController:NavHostController)
{

    val loginFlow = viewModel.loginFlow.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


     error = remember {
        mutableStateOf("")
    }

     errorVisibility = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = loginFlow.value, block = {
                Log.d("fromLoginScreen.kt", loginFlow.value.toString())
                loginFlow.value.let {
                    when(it)
                    {
                        is Resources.Failure -> {
                            circleVisibility.value = false
                            Toast.makeText(
                                context,
                                "Error : ${it.exception.message} ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        Resources.Loading -> {
                            circleVisibility.value = true
                        }

                        is Resources.Success -> {
                            circleVisibility.value = false
                            navController.apply {
                                popBackStack()
                                popBackStack()
                                navigate("home")
                            }
                        }

                        else -> {}

                    }
                }
            } )


    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding->

        Column(modifier = Modifier
            .padding(innerpadding)
            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Box(modifier = Modifier.fillMaxWidth(1f), contentAlignment = Alignment.TopEnd ) {
                    Image(
                        painter = painterResource(id = R.drawable.thali),
                        contentDescription = "background"
                    )
                }
                Column(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(end = 5.dp)
                ) {

                    Text(text = "WELCOME BACK!", fontSize = 40.sp, fontWeight = FontWeight.Black, lineHeight = 40.sp)
                    Text(text= "Sign In To Your Account", fontSize = 20.sp )
                    Spacer(modifier = Modifier.height(20.dp))
                    errorChecker(error, errorVisibility)
                    TextField(
                        modifier = Modifier.fillMaxWidth(1f),
                        value = email, onValueChange = {email=it},//.also { errorVisibility.value = false },
                        placeholder = {Text(text = "Enter Email", fontSize = 16.sp, fontWeight = FontWeight.Normal)},
                        leadingIcon = { Icon(imageVector = Icons.Rounded.Email , contentDescription = "search" )},
                        shape = RoundedCornerShape(8.dp),
                        colors =
                        TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "Email")}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(1f),
                        value = password, onValueChange = {password=it},
                        placeholder = {Text(text = "Enter Password", fontSize = 16.sp, fontWeight = FontWeight.Normal)},
                        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "search" )},
                        shape = RoundedCornerShape(8.dp),
                        colors =
                        TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Password")},

                        )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(onClick = { loginChecker(email,password, viewModel)},
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(ButtonDefaults.MinHeight.plus(5.dp))
                            .padding(horizontal = 8.dp), shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)) {
                        if(circleVisibility.value){
                            loadingcircle()
                        }
                        else
                        {
                            Text(text = "Sign In", fontSize = 20.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Black, color= Color.White)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        //Icon(imageVector = SimpleIcons.Labview , contentDescription = null, tint = Color.Black,modifier = Modifier.scale(2f) )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier =  Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Text(text= "New to ChoolhaChowka?", fontSize = 16.sp, color = Color.DarkGray )
                        TextButton(onClick = { navController.apply { popBackStack()
                            navigate("register") }}) {
                            Text(text= "Register Here", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold )
                        }

                    }




                }
            }
            Text(text= "© ChoolhaChowka, 2023", fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally) )

        }
    }


}




fun loginChecker(email:String,password:String, viewModel: AuthViewModel) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
            if (isEmailValid(email)) {
                    errorVisibility.value = false
                    viewModel.login(email, password)
            }
            else {
                errorVisibility.value = true
                error.value = "Email Invalid"
            }

    }
    else {
        errorVisibility.value = true
        error.value = "Please Fill all Details"
    }
}




















@Composable
fun loadingcircle()
{
    CircularProgressIndicator(
            modifier = Modifier
                .size(30.dp),
        color = Color.White

        )
}



