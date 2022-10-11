package com.example.newsapp.HomeScreens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun Navigation(navController: NavHostController , modifier: Modifier = Modifier){

    NavHost(navController = navController , startDestination =  Screen.Home.route){
        composable(Screen.Home.route)
        {
            HomeScreen(navController)
        }
        composable(Screen.Favorite.route)
        {
            FavoriteScreen(navController = navController)
        }
        composable(Screen.Search.route)
        {
            SearchScreen(navController = navController)
        }

        composable("DetailsScreen?"
        + "urlToImage={urlToImage}"
        + "&description={description}"
        + "&title={title}"
        + "&author={author}"
        + "&publishedAt={publishedAt}"
        ,


            arguments = listOf(
                navArgument(name = "urlToImage")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "description")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "description")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "author")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "publishedAt")
                {
                    type = NavType.StringType
                    defaultValue = ""
                }

            )
        )
        {
            NewsDetailsScreen(
                title        = it.arguments?.getString("title"),
                description  = it.arguments?.getString("description"),
                imgUrl       = it.arguments?.getString("urlToImage"),
                author       = it.arguments?.getString("author"),
                publishedAt  = it.arguments?.getString("publishedAt")

            )
        }

    }
}