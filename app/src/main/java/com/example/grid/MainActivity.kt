package com.example.grid

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid.data.Datasource
import com.example.grid.data.Topic
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridTheme {
                GridApp()
            }
        }
    }
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {
    val window = (LocalView.current.context as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()

    LazyVerticalGrid(
        modifier = modifier.safeDrawingPadding(),
        columns = GridCells.FixedSize(200.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(Datasource.topics + Datasource.topics) {
            GridAppItem(topic = it)
        }
    }
}

@Composable
fun GridAppItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .height(68.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row {
            Image(
                modifier = modifier
                    .width(68.dp)
                    .aspectRatio(1f),
                painter = painterResource(topic.icon),
                contentDescription = stringResource(topic.name)
            )
            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(topic.name),
                    modifier = modifier.padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    ),
                    color = Color(0xFF48454e),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = stringResource(R.string.grain),
                    )
                    Text(
                        text = topic.count.toString(),
                        color = Color(0xFF48454e),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun GridAppItemPreview() {
    GridTheme {
        GridAppItem(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}
