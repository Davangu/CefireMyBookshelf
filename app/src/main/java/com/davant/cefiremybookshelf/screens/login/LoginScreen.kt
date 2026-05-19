package com.davant.cefiremybookshelf.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.R
import kotlin.system.exitProcess

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.TopEnd) {
            Header()
            Logo(Modifier.align(Alignment.Center))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.TopCenter) {
            Body(loginViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Header() {
    IconButton(modifier = Modifier.padding(top=30.dp),
        onClick = { exitProcess(0) }) {
        Icon(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "Close application"
        )
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(modifier = modifier,
        painter = painterResource(R.drawable.logo_mybookshelf),
        contentDescription = "Application logo")
}

@Composable
fun Body(loginViewModel: LoginViewModel) {
    val userName by loginViewModel.userName.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val isLoginEnable by loginViewModel.isLoginEnable.observeAsState(false)
    val isLoginError by loginViewModel.isLoginError.observeAsState(false)
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        UserName(userName, isLoginError) { loginViewModel.onLoginChange(it, password) }
        Password(password, isLoginError) { loginViewModel.onLoginChange(userName, it) }
        Row() {
            RegisterButton() { loginViewModel.registerUser() }
            Spacer(modifier = Modifier.width(24.dp))
            LoginButton(isLoginEnable) { loginViewModel.loginUser() }
        }
        HorizontalDivider(modifier = Modifier.padding(18.dp),
            thickness = 1.dp)
        SocialLogin()
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Text(modifier = modifier
        .padding(bottom = 16.dp),
        text = "Created by David Antolín (v:1.0.0)")
}