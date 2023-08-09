package com.example.newscompose.ui.newdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.newscompose.R
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.ui.compose.Texts
import com.example.newscompose.ui.theme.LightGray
import com.example.newscompose.ui.theme.PrimaryBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewDetailScreen(
    viewModel: NewDetailViewModel = hiltViewModel(),
    article: ArticleDto?,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            NewDetailScreenTopBar(
                onBackPressed = onBackPressed
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .background(PrimaryBlue)
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = article?.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Texts.BodyRegular(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        )
                        .align(Alignment.End),
                    text = article?.source?.name ?: "",
                    color = colorResource(id = R.color.text_gray)
                )

                Texts.BodyRegular(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        ),
                    text = article?.publishedAt ?: "",
                    color = colorResource(id = R.color.text_gray)
                )

                Texts.TitleBold(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        ),
                    text = article?.title ?: "",
                    color = colorResource(id = R.color.dark_gray)
                )

                Texts.BodyRegular(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        ),
                    text = article?.description ?: "",
                    color = colorResource(id = R.color.text_gray)
                )

                Texts.BodyRegular(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        )
                        .align(Alignment.End),
                    text = article?.author ?: "",
                    color = colorResource(id = R.color.text_gray)
                )

                val context = LocalContext.current
                val intent = remember {
                    Intent(Intent.ACTION_VIEW, Uri.parse(article?.url.toString()))
                }

                Texts.BodyRegular(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.size_8dp),
                            end = dimensionResource(id = R.dimen.size_8dp),
                            top = dimensionResource(id = R.dimen.size_16dp)
                        )
                        .align(Alignment.End)
                        .clickable(true) {
                            context.startActivity(intent)
                        },
                    text = article?.url ?: "",
                    color = colorResource(id = R.color.dark_gray)
                )
            }
        }
    )
}

@Composable
fun NewDetailScreenTopBar(
    onBackPressed: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                // Text(text = "Top App Bar")
            },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
            backgroundColor = LightGray,
            contentColor = Color.White,
            elevation = 10.dp,
            actions = {
            }
        )
    }
}
