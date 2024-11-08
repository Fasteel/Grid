package com.example.grid

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
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

    LazyColumn(modifier = modifier.safeDrawingPadding()) {
        items(Datasource.topics.chunked(2) + Datasource.topics.chunked(2)) {
            Row {
                GridAppItem(topic = it[0])
                GridAppItem(topic = it[1])
            }
        }
    }
}

@Composable
fun RowScope.GridAppItem(topic: Topic, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .height(68.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .weight(1f)
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Image(
            modifier = modifier
                .fillMaxHeight()
                .width(68.dp),
            painter = painterResource(topic.icon),
            contentDescription = stringResource(topic.name)
        )
        Column(modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
            Text(
                text = stringResource(topic.name),
                modifier = modifier.padding(0.dp, 16.dp, 16.dp, 8.dp),
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
                    modifier = modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun GridAppItemPreview() {
    GridTheme {
        Row {
            GridAppItem(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
            GridAppItem(topic = Topic(R.string.crafts, 121, R.drawable.crafts))
        }
    }
}
