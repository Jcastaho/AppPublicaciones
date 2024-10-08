package com.straccion.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.straccion.gamermvvmapp.presentation.navigation.AuthScreen
import com.straccion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@Composable
fun LoginBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿No tienes una cuenta?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AuthScreen.Signup.route)
            },
            text = "REGISTRATE AQUI",
            color = Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginBottomBarreview() {
    GamerMVVMAppTheme() {
        LoginBottomBar(rememberNavController())
    }
}