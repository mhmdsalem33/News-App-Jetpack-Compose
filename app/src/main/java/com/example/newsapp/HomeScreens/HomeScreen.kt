package com.example.newsapp.HomeScreens



import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.Utils.Resource
import com.example.newsapp.data.Article
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.viewmodels.HomeViewModel
import com.skydoves.landscapist.ImageLoad
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.runBlocking
import javax.annotation.meta.When


@Composable
fun HomeScreen(navController: NavController){
    Home(navController = navController)
}

@Composable
fun Home(homeMvvm: HomeViewModel = hiltViewModel() , navController: NavController){

    // TODO: 9/29/2022 show First Article from list
       val data by  homeMvvm.getNewsCountryData.observeAsState()
       val first = data?.articles?.first()

       Log.d("testApp" , first.toString())

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
      ) {
        if (first?.urlToImage != null)
        {
           ConstraintLayout {
               val (imgRef , titleRef) = createRefs()
               GlideImage(
                   imageModel = first.urlToImage ,
                   modifier   = Modifier
                       .height(300.dp)
                       .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                       .constrainAs(imgRef)
                       {
                           top.linkTo(parent.top)
                           start.linkTo(parent.start)
                           end.linkTo(parent.end)
                       }
                       .clickable {
                           navController.navigate(
                               "DetailsScreen?" +
                                       "urlToImage=${first.urlToImage}" +
                                       "&description=${first.description}" +
                                       "&title=${first.title}" +
                                       "&author=${first.author}" +
                                       "&publishedAt=${first.publishedAt}"
                           )
                           {
                               popUpTo(navController.graph.findStartDestination().id) {
                                   saveState = true
                               }
                               launchSingleTop = true
                               restoreState = true
                           }
                       }
               )

               Text(
                   text      = first.title,
                   modifier  = Modifier
                       .padding(bottom = 30.dp, start = 30.dp, end = 50.dp)
                       .height(50.dp)
                       .constrainAs(titleRef) {
                           bottom.linkTo(imgRef.bottom)
                           start.linkTo(imgRef.start)
                           end.linkTo(imgRef.end)
                       },
                   color      =  Color.White,
                   fontSize   = 18.sp,
                   fontFamily = Typography.h1.fontFamily
               )
           }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Breaking News" , modifier = Modifier.padding(20.dp) , fontSize = 15.sp  , color = Color.Black , fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(13.dp))

        GetDataNews(navController = navController)

    }
}

@Composable
fun GetDataNews(homeMvvm : HomeViewModel  = hiltViewModel() , navController: NavController)
{
    val data by homeMvvm.getNewsCountryData.observeAsState()
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        ){
            if (data?.articles != null)
            {
                items(data!!.articles)
                {
                    BreakingNews(data = it , navController = navController)
                }
            }
    }
}

@Composable
fun BreakingNews(data : Article, navController: NavController){

    Box(modifier = Modifier
        .padding(end = 10.dp)
        .width(300.dp)
        .wrapContentHeight()
        .clickable {
            navController.navigate(
                "DetailsScreen?" +
                        "urlToImage=${data.urlToImage}" +
                        "&description=${data.description}" +
                        "&title=${data.title}" +
                        "&author=${data.author}" +
                        "&publishedAt=${data.publishedAt}"
            )
            {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            GlideImage(
                imageModel =  data.urlToImage,
                modifier   = Modifier
                    .padding(start = 20.dp)
                    .height(140.dp)
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(25.dp))
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text       = data.title ,
                modifier   = Modifier.padding(start = 25.dp),
                fontSize   = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Typography.h1.fontFamily
            )
            Spacer(modifier = Modifier.height(5.dp))

            if (data.author != null ) // because api have null values
            {
                Text(
                    text       = data.author ,
                    modifier   = Modifier.padding(start = 25.dp),
                    fontSize   = 11.sp,
                    fontFamily = Typography.h1.fontFamily
                )
            }
        }
    }
}
