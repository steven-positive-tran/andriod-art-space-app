package com.example.andriod_art_space_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andriod_art_space_app.ui.theme.AndriodartspaceappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndriodartspaceappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroidArtSpace()
                }
            }
        }
    }
}


@Composable
fun ArtworkImage(modifier: Modifier = Modifier, painter: Painter) {

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .border(2.dp, Color.LightGray)
            .shadow(1.dp, RectangleShape, true, Color.DarkGray, Color.Black)
            .padding(5.dp))


}

@Composable
fun ArtworkDescriptionPanel(title: String, artist: String, year: String,
                            modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(10.dp)
        .background(Color.LightGray)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Row()
        {
            Text(
                text = artist,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = " ($year)",
                fontWeight = FontWeight.Light,
                color = Color.DarkGray
            )
        }
    }

}

@Composable
fun UIButtons(prevButton: () -> Unit, nextButton: () -> Unit,
              modifier: Modifier = Modifier){

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly)
    {

        Button(
            onClick = prevButton,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            content = {
                Text(
                    text = "Previous"
                )
            },
        )

        Button(
            onClick = nextButton,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            content = {
                Text(
                    text = "Next"
                )
            },
        )

    }

}

@Composable
fun AndroidArtSpace() {

    var artistId by remember {
        mutableStateOf(0)
    }
    var title : String
    var artist : String
    var year: String
    var image: Painter


    //TODO REPLACE
    when (artistId){

        0 ->{
            image = painterResource(id = R.drawable.fruit__jug__and_a_glass_1943_7_4)
            title = stringResource(id = R.string.title_1)
            artist = stringResource(id = R.string.artist_1)
            year = stringResource(id = R.string.year_1)
        }
        1 ->{
            image = painterResource(id = R.drawable.portrait_of_a_young_man_and_his_tutor_1961_9_26)
            title = stringResource(id = R.string.title_2)
            artist = stringResource(id = R.string.artist_2)
            year = stringResource(id = R.string.year_2)
        }
        2-> {
            image = painterResource(id = R.drawable.the_attentive_nurse_1952_5_37)
            title = stringResource(id = R.string.title_3)
            artist = stringResource(id = R.string.artist_3)
            year = stringResource(id = R.string.year_3)
        }
        else -> {
            image = painterResource(id = R.drawable.fruit__jug__and_a_glass_1943_7_4)
            title = stringResource(id = R.string.title_1)
            artist = stringResource(id = R.string.artist_1)
            year = stringResource(id = R.string.year_1)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ArtworkImage(Modifier.weight(4f), painter = image)
        ArtworkDescriptionPanel(title, artist, year = year,
            Modifier.weight(1f))

        UIButtons({
                  if (artistId > 0)
                      artistId--
                  },
            { artistId++}, Modifier.weight(1f))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndriodartspaceappTheme {
        AndroidArtSpace()
    }
}