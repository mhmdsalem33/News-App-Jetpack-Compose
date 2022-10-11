package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.HomeScreens.Navigation
import com.example.newsapp.HomeScreens.Screen
import com.example.newsapp.Utils.Resource
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val homeMvvm : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                MyNavigation()
            }
        }
    }
}

@Composable
fun MyNavigation()
{
    val navController =  rememberNavController()
    val items         =  listOf(Screen.Home  , Screen.Favorite , Screen.Search)
    Scaffold(
        bottomBar = {
            BottomNavigation (
                modifier         = Modifier.border(border = BorderStroke(width = 1.dp , Color.LightGray)),
                backgroundColor  = Color.White,
                    ){
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach{ screen ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = "Icons" )},
                        label = { Text(text = screen.title)},
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop  =  true
                                restoreState     =  true
                            }
                        })
                }
            }
        }
    ) { innerPadding ->
        Navigation(navController = navController , modifier = Modifier.padding(innerPadding) )
    }
}
