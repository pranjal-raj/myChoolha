package com.example.mychoolha.Presentation.screens.LoginSignupFlow


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mychoolha.Presentation.navigation.myNavGraph
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel : AuthViewModel, navController:NavHostController)
{

    val loginFlow = viewModel.loginFlow.collectAsState()
    var phoneNumber by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
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

                    TextField(
                        modifier = Modifier.fillMaxWidth(1f),
                        value = "", onValueChange = {phoneNumber=it},
                        placeholder = {Text(text = "Enter Mobile Number", fontSize = 16.sp, fontWeight = FontWeight.Normal)},
                        leadingIcon = { Icon(imageVector = Icons.Rounded.Phone , contentDescription = "search" )},
                        shape = RoundedCornerShape(8.dp),
                        colors =
                        TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text(text = "Mobile Number")}
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(1f),
                        value = "", onValueChange = {phoneNumber=it},
                        placeholder = {Text(text = "Enter Password", fontSize = 16.sp, fontWeight = FontWeight.Normal)},
                        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "search" )},
                        shape = RoundedCornerShape(8.dp),
                        colors =
                        TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text(text = "Password")},

                        )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(onClick = {viewModel.login("$phoneNumber@gmail.com", password)}, modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(ButtonDefaults.MinHeight.plus(5.dp))
                        .padding(horizontal = 8.dp), shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)) {
                        Text(text = "Sign In", fontSize = 20.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Black, color= Color.White)
                        Spacer(modifier = Modifier.padding(10.dp))
                        //Icon(imageVector = SimpleIcons.Labview , contentDescription = null, tint = Color.Black,modifier = Modifier.scale(2f) )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier =  Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Text(text= "New to ChoolhaChowka?", fontSize = 16.sp, color = Color.DarkGray )
                        TextButton(onClick = { navController.navigate("register")}) {
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

    loginFlow.value.let {
        when(it)
        {
            is Resources.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, "Error : ${it.exception.message} ", Toast.LENGTH_SHORT)
            }
            Resources.Loading -> {
                CircularProgressIndicator()
            }
            is Resources.Success -> {
                navController.navigate("home").apply {
                    navController.popBackStack()
                }
            }
            else -> {}
        }
    }
}

