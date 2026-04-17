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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.R
import kotlin.system.exitProcess

@Composable
fun LoginScreen(doLogin: (String) -> Unit) {
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
            Body(doLogin)
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
fun Body(doLogin: (String) -> Unit) {
    var userName by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        UserName(userName) { userName = it }
        Password()
        Row() {
            RegisterButton()
            Spacer(modifier = Modifier.width(24.dp))
            LoginButton(userName, doLogin)
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