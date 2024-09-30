package com.straccion.gamermvvmapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.presentation.components.DefaultButton
import com.straccion.gamermvvmapp.presentation.components.DefaultDialog
import com.straccion.gamermvvmapp.presentation.components.DefaultOutlinedTextField
import com.straccion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.straccion.gamermvvmapp.presentation.ui.theme.DarkGray900
import com.straccion.gamermvvmapp.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(
    navController: NavHostController,
    viewModel: ProfileEditViewModel = hiltViewModel()
) {

    val state = viewModel.state

    viewModel.resultingActivityHandler.handle()
    var dialogState = remember {
        mutableStateOf(false)
    }

    DefaultDialog(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Red500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(130.dp))
                if (viewModel.state.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(140.dp)  // Ajuste del tamaño
                            .clip(CircleShape)  // Mantener la forma circular
                            .clickable {
                                dialogState.value = true
                            }
                            .border(
                                2.dp,
                                Color.Transparent,
                                CircleShape
                            ),  // Añadir un borde si quieres
                        model = viewModel.state.image,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Imagen de usuario"
                    )

                } else {
                    Image(
                        modifier = Modifier
                            .height(140.dp)
                            .clickable {
                                dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user"
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = DarkGray900), // Cambia el color de fondo
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 300.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    text = "ACTUALIZAR DATOS",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(20.dp))
                DefaultOutlinedTextField(
                    modifier = Modifier,
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nombre de Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "ACTUALIZAR DATOS",
                    onClick = { viewModel.saveImage() }
                )
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }
}

