package com.example.themovieapp.presentation.ui.fragments.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.themovieapp.BuildConfig
import com.example.themovieapp.data.PATH_IMAGES



@Composable
fun MovieDetailScreen(
    path: String,
    tituloMovie: String,
    overview: String,
    listGender: ArrayList<String> = arrayListOf()
) {

    /*LaunchedEffect(Unit){
        viewModel.gendersFromDB.collect{
            when(it){
                is DataState.Loading -> {}
                is DataState.Success -> {
                    listGender?.add(it.data)
                }
                is DataState.Error -> {}

                else -> {}
            }
        }
    }*/
    
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val painter = rememberImagePainter(
            data = BuildConfig.BASE_URL_IMAGES.plus(PATH_IMAGES).plus(path)/*"https://www.nationalgeographic.com.es/medio/2022/12/12/perro-1_514aad3b_221212161023_1280x720.jpg"*/,
            builder = {
                // Configure opciones de Coil si se necesitan, como cache, redimensionamiento, etc.
            }
        )

        Image(
            painter = painter,
            contentDescription = "Mi imagen",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )

        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = tituloMovie, color = Color.White, fontSize = 28.sp, fontStyle = FontStyle.Normal,  modifier = Modifier.padding(16.dp)) }
            Text(text = overview , color = Color.White, fontSize = 18.sp, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart))

            LazyColumn {
                items(listGender.size) { gender ->
                    Text(text = gender.toString())
                }
            }

        }
    }

