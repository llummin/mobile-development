package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAPPTheme

class Card (
    image : Int,
    name : Int,
    author: Int,
    year: Int
) {
    val image = image
    val name = name
    val author = author
    val year = year
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier){
    val list = ArrayList<Card>(3)
    list.add(Card(
        R.drawable.sallya,
        R.string.image_name_1,
        R.string.author_1,
        R.string.year_1
    ))

    list.add(Card(
        R.drawable.sallyo,
        R.string.image_name_2,
        R.string.author_2,
        R.string.year_2
    ))

    list.add(Card(
        R.drawable.sallyaghh,
        R.string.image_name_3,
        R.string.author_3,
        R.string.year_3
    ))

    list.add(Card(
        R.drawable.sallyeye,
        R.string.image_name_4,
        R.string.author_4,
        R.string.year_4
    ))

    var currentId by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ArtCard(
                image = list[currentId].image,
                name = list[currentId].name,
                author = list[currentId].author,
                year = list[currentId].year,
                onNext = {
                    if (currentId == list.size - 1){
                        currentId = 0
                    } else {
                        currentId += 1
                    }
                },
                onPrevious = {
                    if (currentId == 0){
                        currentId = list.size - 1
                    } else {
                        currentId -= 1
                    }
                },
                modifier = modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun ArtCard(
    modifier: Modifier = Modifier,
    image: Int,
    name: Int,
    author: Int,
    year: Int,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .shadow(2.dp)
                    .padding(horizontal = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painterResource(image),
                        null,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(Color(0xFFE0D1FF))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ){
                Text(text = stringResource(name), fontSize = 16.sp, fontWeight = FontWeight.Light)
                Row() {
                    Text(
                        text = stringResource(author),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "(${stringResource(year)})", fontSize = 14.sp)
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(bottom = 20.dp)
        ) {
            Button(
                onClick = onPrevious
            ) {
                Text(text = "Назад", color = Color.White)
            }
            Button(
                onClick = onNext
            ) {
                Text(text = "Вперед", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAPPTheme {
        ArtSpaceApp()
    }
}