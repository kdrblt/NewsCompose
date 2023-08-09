package com.example.newscompose.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewListItem(
    title: String? = null,
    imageUrl: String? = null,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onItemClicked()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.LightGray)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                model = imageUrl,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
