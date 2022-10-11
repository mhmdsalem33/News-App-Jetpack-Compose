package com.example.newsapp.HomeScreens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.data.Article
import com.example.newsapp.data.Favorite
import com.example.newsapp.data.Source
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.viewmodels.FavoriteScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsDetailsScreen(
    title :String?  , description :String?, imgUrl :String? , author : String? , publishedAt : String? ,
    favoriteMvvm : FavoriteScreenViewModel = hiltViewModel()
){



    ConstraintLayout(modifier = Modifier
        .padding(bottom = 56.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        val (imgRef , footerRef , imageDetailsRef) = createRefs()

       GlideImage(
           imageModel = imgUrl.toString() ,
           modifier   = Modifier
               .fillMaxWidth()
               .height(400.dp)
               .constrainAs(imgRef)
               {
                   top.linkTo(parent.top)
                   start.linkTo(parent.start)
                   end.linkTo(parent.end)
               })

        // TODO: 9/30/2022 Box Inside image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(imageDetailsRef)
                {
                    bottom.linkTo(imgRef.bottom)
                    start.linkTo(imgRef.start)
                    end.linkTo(imgRef.end)
                }
        ) {
            DetailsInsideImage(title, author)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(footerRef)
                {
                    top.linkTo(imgRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        {
           Row(
               modifier              = Modifier.fillMaxWidth(),
               verticalAlignment     = Alignment.CenterVertically ,
               horizontalArrangement = Arrangement.SpaceBetween) {
               Text(
                   text        = "Published at "+publishedAt.toString(),
                   fontSize    = 11.sp,
                   color       = Color.Black,
                   modifier    = Modifier.padding(start = 30.dp , bottom = 5.dp , end = 30.dp),
                   fontWeight  = FontWeight.Bold,
                   fontFamily  = Typography.h1.fontFamily
               )
               Image(
                   imageVector        = Icons.Default.FavoriteBorder,
                   contentDescription = "Favorite" ,
                   modifier           = Modifier
                       .padding(end = 10.dp)
                       .clickable {
                           val saveArticle = Favorite(
                               author.toString(),
                               "",
                               description.toString(),
                               publishedAt.toString(),
                               title.toString(),
                               "",
                               imgUrl.toString()
                           )
                            if (saveArticle != null)
                            {
                                Log.d("testApp" , "Saved")
                                favoriteMvvm.upsertArticle(saveArticle)
                            }





                       }
               )
           }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text        = author.toString(),
                fontSize    = 17.sp,
                color       = Color.Black,
                modifier    = Modifier.padding(start = 30.dp , bottom = 5.dp , end = 30.dp),
                fontWeight  = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text        = title.toString(),
                fontSize    = 15.sp,
                color       = Color.Black,
                modifier    = Modifier.padding(start = 30.dp , bottom = 5.dp , end = 30.dp),
                fontFamily  = Typography.h1.fontFamily
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text        = description.toString(),
                fontSize    = 15.sp,
                color       = Color.Black,
                modifier    = Modifier.padding(start = 30.dp , bottom = 5.dp , end = 30.dp),
                fontFamily  = Typography.h1.fontFamily
            )
        }
    }
}

@Composable
fun DetailsInsideImage(title: String? , author: String?){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text        = title.toString(),
            fontSize    = 20.sp,
            fontWeight  = FontWeight.Bold,
            color       = Color.White,
            modifier    = Modifier.padding(start = 30.dp , end = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text        = author.toString(),
            fontSize    = 12.sp,
            color       = Color.White,
            modifier    = Modifier.padding(start = 30.dp, bottom = 5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .height(50.dp)
                .background(Color.White)
        )
        {}
    }
}


