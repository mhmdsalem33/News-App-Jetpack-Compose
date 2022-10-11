package com.example.newsapp.HomeScreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route:String , val title :String , val icon :ImageVector)
{
      object Home : Screen(
          route =   "home",
          title =   "Home",
          icon  =   Icons.Default.Home )

      object Favorite : Screen(
          route = "favorite"   ,
          title = "Favorites"  ,
          icon  = Icons.Default.FavoriteBorder)

      object Search : Screen(
        route   = "search" ,
        title   = "Search" ,
        icon    =  Icons.Default.Search )

}
