package com.example.andriod_art_space_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.andriod_art_space_app.data.DataSource
import com.example.andriod_art_space_app.model.Painting
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
                    AndroidArtSpace(DataSource().loadPaintings())
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
fun ArtworkDisplay(modifier: Modifier = Modifier,
painting: Painting)
{
    Column(modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        ArtworkImage(Modifier.weight(4f), painter = painterResource(id = painting.imageResource))
        ArtworkDescriptionPanel(
            stringResource(id = painting.title),
            stringResource(id = painting.artistName),
            stringResource(id = painting.year),
            Modifier.weight(1f))
    }

}


@Composable
fun AndroidArtSpace(paintingList: List<Painting>) {

    var artistId by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        //TODO Find a better solution for the vertical Scroll
        ArtworkDisplay(
            Modifier
                .weight(4f)
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
            ,paintingList[artistId])
        UIButtons({
                  if (artistId > 0)
                      artistId--
                  },
            { artistId++}, Modifier.weight(1f, false))
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndriodartspaceappTheme {
        AndroidArtSpace(DataSource().loadPaintings())
    }
}