package com.straccion.gamermvvmapp.presentation.screens.update_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.straccion.gamermvvmapp.R
import com.straccion.gamermvvmapp.presentation.components.DefaultDialog
import com.straccion.gamermvvmapp.presentation.components.DefaultOutlinedTextField
import com.straccion.gamermvvmapp.presentation.screens.update_post.UpdatePostViewModel
import com.straccion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.straccion.gamermvvmapp.presentation.ui.theme.Red500



@Composable
fun UpdatePostContent(viewModel: UpdatePostViewModel = hiltViewModel()) {

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
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp)
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.navigationBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Red500)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (viewModel.state.image != "") {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()  // Ajuste del tamaño
                                .height(250.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            model = viewModel.state.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Imagen de usuario"
                        )

                    } else {
                        Image(
                            modifier = Modifier
                                .height(175.dp)
                                .padding(top = 35.dp, bottom = 10.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "add imagen"
                        )
                        Text(
                            text = "SELECCIONA UNA IMAGEN",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            DefaultOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = state.name,
                onValueChange = { viewModel.onNameInput(it) },
                label = "Nombre del juego",
                icon = Icons.Default.Face,
                errorMsg = "",
                validateField = {
                }
            )
            DefaultOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = state.description,
                onValueChange = { viewModel.onDescriptionInput(it) },
                label = "Descripción",
                icon = Icons.Default.Face,
                errorMsg = "",
                validateField = {
                }
            )
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                text = "CATEGORIAS"
            )
            viewModel.radioOption.forEach{ option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (option.category == state.category),
                            onClick = {
                                viewModel.onCategoryInput(option.category)
                            }
                        )
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option.category == state.category),
                        onClick = {
                            viewModel.onCategoryInput(option.category)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.weight(1f), // Ajusta el tamaño del texto
                        text = option.category
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        modifier = Modifier.height(40.dp),
                        painter = painterResource(id = option.image),
                        contentDescription = option.category
                    )
                }
            }
        }
    }
}