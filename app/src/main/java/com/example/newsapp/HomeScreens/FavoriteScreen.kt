package com.example.newsapp.HomeScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.data.Favorite
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.viewmodels.FavoriteScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FavoriteScreen( favoriteMvvm : FavoriteScreenViewModel = hiltViewModel() , navController: NavController){

    val savedProducts by favoriteMvvm.readSavedNews().observeAsState(initial = emptyList())


  Column(modifier = Modifier.padding(start = 20.dp , end = 10.dp , top = 20.dp , bottom = 56.dp)) {
      Text(
          text = "Favorites News"
      )

      LazyVerticalGrid(
          columns           =  GridCells.Adaptive(minSize = 150.dp),
          contentPadding    =  PaddingValues(8.dp)
          )
      {
                items(savedProducts)
                {
                    FavoriteItemsDesign(it , navController = navController)
                }
      }
  }
}

@Composable
fun FavoriteItemsDesign(data : Favorite ,favoriteMvvm : FavoriteScreenViewModel = hiltViewModel(),navController: NavController ){


    ConstraintLayout{
        val iconRef = createRef()

        Column(modifier    = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("DetailsScreen?"+
                        "urlToImage=${data.urlToImage}"+
                        "&description=${data.description}"+
                        "&title=${data.title}"+
                        "&author=${data.author}"+
                        "&publishedAt=${data.publishedAt}" )
                {
                    popUpTo(Screen.Favorite.route){
                        saveState   = true
                    }
                    launchSingleTop = true
                    restoreState    = true
                }
            }

        ) {
            GlideImage(
                imageModel = data.urlToImage,
                modifier   = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(150.dp)
            )
            Text(
                text        = data.title,
                fontFamily  = Typography.h1.fontFamily,
                fontSize    = 10.sp
            )
        }

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            modifier = Modifier
                .padding(end = 10.dp , top = 10.dp)
                .constrainAs(iconRef)
                {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
                .clickable {
                    favoriteMvvm.deleteArticle(data)
                }
            ,
            tint = Color.White
        )

    }



}