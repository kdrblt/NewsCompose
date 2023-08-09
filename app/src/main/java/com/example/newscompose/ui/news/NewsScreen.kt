package com.example.newscompose.ui.news

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.newscompose.R
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.ui.dialog.CustomDateRangePickerDialog
import com.example.newscompose.ui.internetconnection.ConnectionState
import com.example.newscompose.ui.internetconnection.connectivityState
import com.example.newscompose.ui.theme.Green
import com.example.newscompose.ui.theme.LightGray
import com.example.newscompose.ui.theme.PrimaryOrange
import com.example.newscompose.ui.theme.Red

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navigateToNewDetail: (ArticleDto) -> Unit
) {
    val state = viewModel.state.value
    val showDialog = remember { mutableStateOf(false) }
    val isDateFiltered = remember { mutableStateOf(false) }
    val connection by connectivityState()
    val articles = viewModel.getNews(
        connection,
        isDateFiltered.value
    ).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = state.topBarFilteredText,
                connection = connection,
                showRemoveFilterIcon = state.isFiltered,
                showLeftNavIcon = false
            ) {
                isDateFiltered.value = false
                viewModel.filterRemoved()
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.filter_news)) },
                backgroundColor = PrimaryOrange,
                onClick = {
                    showDialog.value = true
                },
                icon = { Icon(painterResource(id = R.drawable.ic_filter), null) }
            )
        }
    ) {
        LazyColumn {
            items(items = articles) { article ->
                NewListItem(
                    article?.title,
                    article?.urlToImage
                ) {
                    article?.let { it1 -> navigateToNewDetail.invoke(it1) }
                }
                Divider()
            }
        }

        // First Loading
        when (val state = articles.loadState.refresh) {
            is LoadState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = state.error.message.toString()
                    )
                }
            }

            is LoadState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color.Black)
                }
            }

            is LoadState.NotLoading -> {
                if (articles.itemCount == 0) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.empty_List)
                        )
                    }
                }
            }
        }

        // Paging
        when (val state = articles.loadState.append) {
            is LoadState.Error -> {
                ShowToastMessage(state.error.message.toString())
            }

            is LoadState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color.Black)
                }
            }

            else -> {}
        }

        ShowDateRangePickerDialog(
            showDialog = showDialog.value,
            onDismiss = {
                showDialog.value = it
            },
            onSave = { start, end ->
                isDateFiltered.value = true
                viewModel.changeDateFiltered(start, end)
                showDialog.value = false
            }
        )
    }
}

@Composable
fun MyTopAppBar(
    title: String,
    connection: ConnectionState,
    showLeftNavIcon: Boolean = false,
    showRemoveFilterIcon: Boolean = false,
    onRemoveFilterClicked: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 14.sp
                )
            },
            navigationIcon = {
                if (showLeftNavIcon) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            },
            backgroundColor = LightGray,
            contentColor = Color.White,
            elevation = 10.dp,
            actions = {
                if (showRemoveFilterIcon) {
                    IconButton(
                        onClick = {
                            onRemoveFilterClicked()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                }
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        painterResource(id = R.drawable.ic_network),
                        contentDescription = null,
                        tint = if (connection == ConnectionState.Available) {
                            Green
                        } else {
                            Red
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun ShowDateRangePickerDialog(
    showDialog: Boolean,
    onDismiss: (Boolean) -> Unit,
    onSave: (Long, Long) -> Unit
) {
    CustomDateRangePickerDialog(
        showDialog = showDialog,
        onSelected = { start, end ->
            onSave(start, end)
        },
        onDialogDismiss = {
            onDismiss(it)
        }
    )
}

@Composable
fun ShowToastMessage(
    message: String
) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}
