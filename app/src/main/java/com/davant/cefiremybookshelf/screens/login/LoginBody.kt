package com.davant.cefiremybookshelf.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.R

@Composable
fun UserName(userName:String, updateUserName: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 48.dp,
            vertical = 12.dp),
        value = userName,
        onValueChange = { updateUserName(it) },
        placeholder = { Text(text = "User name",
            color = Color.Gray,
            fontStyle = FontStyle.Italic)})
}

@Composable
fun Password() {
    var password by rememberSaveable { mutableStateOf("") }
    var passVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 48.dp,
            vertical = 12.dp),
        value = password,
        onValueChange = { password = it },
        placeholder = { Text(text = "Password",
            color = Color.Gray,
            fontStyle = FontStyle.Italic)},
        trailingIcon = {
            val eyeIcon = if(passVisibility) R.drawable.ic_visible
                else R.drawable.ic_not_visible
            Icon(painter = painterResource(eyeIcon),
                contentDescription = if(passVisibility) "Password is visible"
            else "Passowrd is hidden",
                modifier = Modifier.clickable { passVisibility = !passVisibility})
        },
        visualTransformation = if(passVisibility) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}
@Composable
fun RegisterButton() {
    Button(modifier = Modifier.width(100.dp),
        onClick = {
            // Registrar nuevo usuario
        }) {
        Text("Register")
    }
}

@Composable
fun LoginButton(userName:String, doLogin: (String) -> Unit) {
    Button(modifier = Modifier.width(100.dp),
        onClick = { doLogin(userName) }) {
        Text("Login")
    }
}

@Composable
fun SocialLogin() {
    Row(modifier = Modifier.clickable{
        // Acción para loguearse con Google
    }) {
        Image(modifier = Modifier
            .size(24.dp),
            painter = painterResource(R.drawable.google_logo),
            contentDescription = "Google logo")
        Text("Login with Google",
            fontStyle = FontStyle.Italic,
            modifier = Modifier
            .padding(horizontal = 8.dp))
    }
}