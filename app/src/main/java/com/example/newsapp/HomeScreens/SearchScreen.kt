package com.example.newsapp.HomeScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

import com.example.newsapp.R
import com.example.newsapp.data.Article
import com.example.newsapp.ui.theme.IconColor
import com.example.newsapp.ui.theme.TextColor
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.viewmodels.SearchViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


var category = mutableStateOf("")
@Composable
fun SearchScreen(searchMvvm : SearchViewModel = hiltViewModel() , navController: NavController){


    val scope = rememberScaffoldState()
    Column(
        modifier    = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 56.dp)
           // .verticalScroll(rememberScrollState())

    ) {

        var searchText by rememberSaveable{ mutableStateOf("")}
        if (searchText.isEmpty())
        {
            if (category.value.isEmpty())
            {
                searchMvvm.getNewsByCategory("us" , "general" , "fa9afc82f3c941da9bf12a2b1c9b6552")
            }
            else
            {
                searchMvvm.getNewsByCategory("us" , category.value , "fa9afc82f3c941da9bf12a2b1c9b6552")
            }
        }
        else
        {
            LaunchedEffect(true) {
                searchMvvm.getSearchNews(searchText)
                delay(1000)
            }

        }



         val   dataCategories by searchMvvm.getNewsByCategoryData.observeAsState()
         val   searchData     by searchMvvm.getSearchData.observeAsState()
        Log.d("testApp" , searchData?.articles.toString())

            LazyColumn{
                item{
                    Text(
                        text       = "Discover",
                        color      =  Color.Black,
                        fontWeight =  FontWeight.Bold,

                        )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text       = "News from all over the world",
                        color      =  TextColor,
                        fontWeight =  FontWeight.Bold,
                        fontSize   =  13.sp,
                        fontFamily =  Typography.h1.fontFamily,
                    )

                    Spacer(modifier = Modifier.height(20.dp))



                    TextField(
                        value         =  searchText,
                        onValueChange = {searchText = it},
                        leadingIcon = {
                            IconButton(onClick = {/*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor          = Color(0xD3E0E0E0),
                            textColor                = Color.Black,
                            unfocusedIndicatorColor  = Color.Transparent,
                            focusedIndicatorColor    = Color.Transparent,
                            cursorColor              = Color.Black,
                            unfocusedLabelColor      = Color.Black,
                            focusedLabelColor        = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)),
                        singleLine = true,
                        label = { Text(text = "Search ...")},
                        textStyle = Typography.h1

                    )

                    ChipGroupCompose()
                    Spacer(modifier = Modifier.height(10.dp))
                }


                if (searchText.isEmpty())
                {
                    dataCategories?.articles?.let {
                        items(dataCategories!!.articles){
                            Design(data = it , navController = navController)
                        }
                    }
                }
                else
                {
                    searchData?.articles?.let {
                        items(searchData!!.articles)
                        {
                            Design(data = it , navController = navController)
                        }
                    }
                }
            }
    }
}




@Composable
fun ChipGroupCompose()
{
    val chipList   :List<String> = listOf(
        "general" ,
        "business",
        "sports" ,
        "technology",
        "entertainment"
    )

    var selected by remember{ mutableStateOf("")}

    Row(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    )
    {
        chipList.forEach { it ->
            Chip(
                title      =  it ,
                selected   =  selected  ,
                onSelected =
                {
                    selected = it }
            )
        }
    }


}    @Composable
fun Chip(
    title      :  String ,
    selected   :  String ,
    onSelected : (String) -> Unit,
    searchMvvm: SearchViewModel = hiltViewModel()
    )
{
    val isSelected = selected == title
    val background   =  if (isSelected) IconColor   else Color.LightGray
    val contentColor =  if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(end = 5.dp)
            .height(35.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    onSelected(title)
                    // searchMvvm.getNewsByCategory("us", title, "eccf8a92092c47b0957fbba2cbf0a95a")
                    category.value = title
                })
    ) {
        Row(
            modifier =
            Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AnimatedVisibility(visible = isSelected) {
                Icon(imageVector       = Icons.Filled.Check, contentDescription = "Check" , tint = Color.White )
            }

            Text(
                text       = title ,
                color      = contentColor ,
                fontSize   = 16.sp,
                fontFamily = Typography.h1.fontFamily
            )
        }
    }
}


@Composable
fun Design(data : Article , navController: NavController){

       Row(
           modifier = Modifier
               .fillMaxWidth()
               .height(100.dp)
               .padding(bottom = 5.dp)
               .clickable {
                   navController.navigate("DetailsScreen?"+
                           "urlToImage=${data.urlToImage}"+
                           "&description=${data.description}"+
                           "&title=${data.title}"+
                           "&author=${data.author}"+
                           "&publishedAt=${data.publishedAt}" )
                   {
                       popUpTo(Screen.Search.route){
                           saveState = true
                       }
                       launchSingleTop  =  true
                       restoreState     =  true
                   }
               },
           verticalAlignment     = Alignment.CenterVertically,
           //  horizontalArrangement = Arrangement.SpaceBetween
       )
       {

           if (data.urlToImage != null)
           {
               GlideImage(
                   imageModel = data.urlToImage,
                   modifier   = Modifier
                       .clip(RoundedCornerShape(10.dp))
                       .size(100.dp),
               )
           }
           Column(modifier = Modifier.padding(start = 10.dp)) {

               if (data.author != null)
               {
                   Text(
                       text       = data.author,
                       fontFamily = Typography.h1.fontFamily,
                       fontWeight = FontWeight.Bold,
                       fontSize = 15.sp
                   )
               }
               Spacer(modifier = Modifier.height(5.dp))

               if (data.title != null)
               {
                   Text(
                       text       = data.title,
                       fontFamily = Typography.h1.fontFamily,
                       fontWeight = FontWeight.Bold,
                       fontSize   = 11.sp
                   )
               }
           }
       }

}