package com.example.mychoolha.Presentation.screens.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.mychoolha.viewModel.AuthViewModel

@Composable
fun home(authViewModel: AuthViewModel)
{

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Welcome User \n ${authViewModel.currentUser?.displayName}")
    }

}