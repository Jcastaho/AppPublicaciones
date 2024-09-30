package com.straccion.gamermvvmapp.presentation.screens.detail_post.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.straccion.gamermvvmapp.presentation.screens.detail_post.DetailPostViewModel
import com.straccion.gamermvvmapp.presentation.ui.theme.Red500

@Composable
fun DetailPostContent(navController: NavHostController, viewModel: DetailPostViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
    ) {
        Box(){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                model = viewModel.post.image,
                contentDescription = ""
            )

            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(top = 5.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        if (!viewModel.post.user?.image.isNullOrBlank()){
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        model = viewModel.post.user?.image ?: "",
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 7.dp, start = 20.dp)
                    ) {
                        Text(
                            fontSize = 13.sp,
                            text = viewModel.post.user?.username ?: "Username not available"
                        )
                        Text(
                            fontSize = 11.sp,
                            text = viewModel.post.user?.email ?: "Email not available"
                        )
                    }
                }
            }
        }else {
            Spacer(modifier = Modifier.height(20.dp))
        }
        Text(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 15.dp, top = 10.dp),
            color = Red500,
            fontSize = 22.sp,
            text = viewModel.post.name.uppercase(),
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier
                .padding(start = 13.dp, bottom = 15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 7.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically, // Alinea los elementos verticalmente
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp) // Ajusta el tamaño de la imagen
                        .padding(end = 8.dp),
                    painter = painterResource(
                        id = when (viewModel.post.category) {
                            "PC" -> R.drawable.icon_pc
                            "XBOX" -> R.drawable.icon_xbox
                            "PS4" -> R.drawable.icon_ps4
                            "NINTENDO" -> R.drawable.icon_nintendo
                            else -> R.drawable.control
                        }
                    ),
                    contentDescription = "Category Icon"
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    fontWeight = FontWeight.Bold,
                    text = viewModel.post.category,
                    fontSize = 17.sp
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp),
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            text = "DESCRIPCIÓN",
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp),
            text = viewModel.post.description,
            fontSize = 14.sp
        )
    }

}