package com.straccion.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.presentation.components.DefaultButton
import com.straccion.gamermvvmapp.presentation.components.DefaultOutlinedTextField
import com.straccion.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.straccion.gamermvvmapp.presentation.ui.theme.DarkGray900
import com.straccion.gamermvvmapp.presentation.ui.theme.Red500

@Composable
fun LoginContent(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .background(Red500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Image(
                    modifier = Modifier
                        .height(140.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control"
                )
                Text(
                    text = "FIREBASE MVVM"
                )
            }
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = DarkGray900), // Cambia el color de fondo
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 240.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 40.dp),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(25.dp))
                DefaultOutlinedTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )
                DefaultOutlinedTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    text = "INICIAR SESION",
                    onClick = {
                        viewModel.login()
                    },
                    enable = viewModel.isEnableLoginButton
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
