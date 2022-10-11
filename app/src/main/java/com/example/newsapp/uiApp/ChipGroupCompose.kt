package com.example.newsapp

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Chip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.IconColor
import com.example.newsapp.ui.theme.Typography
import com.google.android.material.chip.ChipGroup

/*
@Composable
fun ChipGroupCompose()
{
    val chipList   :List<String> = listOf(
        "general" ,
        "health",
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
    onSelected : (String) -> Unit)
{
    val isSelected = selected == title
    val background   =  if (isSelected) IconColor else Color.LightGray
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
                    Log.d("testApp", title)

                })
    ) {
        Row(
            modifier =
            Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AnimatedVisibility(visible = isSelected) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Check" , tint = Color.White )
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


 */