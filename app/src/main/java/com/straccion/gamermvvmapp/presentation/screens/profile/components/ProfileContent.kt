package com.straccion.gamermvvmapp.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.presentation.MainActivity
import com.straccion.gamermvvmapp.presentation.components.DefaultButton
import com.straccion.gamermvvmapp.presentation.navigation.AuthScreen
import com.straccion.gamermvvmapp.presentation.navigation.DetailsScreen
import com.straccion.gamermvvmapp.presentation.navigation.Graph
import com.straccion.gamermvvmapp.presentation.screens.profile.ProfileViewModel


@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,

            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                Spacer(modifier = Modifier.height(50.dp))
                if (viewModel.userData.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(135.dp)  // Ajuste del tamaño
                            .clip(CircleShape)  // Mantener la forma circular
                            .border(
                                1.dp,
                                Color.Transparent,
                                CircleShape
                            ),  // Añadir un borde si quieres
                        model = viewModel.userData.image,
                        contentDescription = "UserImage",
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .size(135.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = ""
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Log.e("hola", viewModel.userData.username)
        Text(
            text = viewModel.userData.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        )
        DefaultButton(
            modifier = Modifier
                .padding(top = 50.dp)
                .width(220.dp),
            text = "Editar datos",
            color = Color.Gray,
            icon = Icons.Default.Edit,
            onClick = {
                navController.navigate(route = DetailsScreen.ProfileEdit.passUser(viewModel.userData.toJson()))
            }
        )
        DefaultButton(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(220.dp),
            text = "Cerrar sesion",
            onClick = {
                viewModel.logout()
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )

    }
}
