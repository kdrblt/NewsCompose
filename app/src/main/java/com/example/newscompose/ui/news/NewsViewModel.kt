package com.example.newscompose.ui.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newscompose.common.DateHelper
import com.example.newscompose.domain.dto.ArticleDto
import com.example.newscompose.domain.repository.NewsLocalRepository
import com.example.newscompose.domain.repository.NewsRemoteRepository
import com.example.newscompose.domain.usecase.GetRemoteNews
import com.example.newscompose.ui.internetconnection.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository,
    private val newsLocalRepository: NewsLocalRepository,
    private val getRemoteNews: GetRemoteNews
) : ViewModel() {

    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    fun getNews(
        connection: ConnectionState,
        isDateFiltered: Boolean
    ): Flow<PagingData<ArticleDto>> {
        return getRemoteNews.invoke(
            GetRemoteNews.Params(
                connection,
                isDateFiltered
            )
        ).cachedIn(viewModelScope)
    }

    fun changeDateFiltered(start: Long, end: Long) {
        DateHelper.fromDate = DateHelper.convertLongToDate(start)
        DateHelper.toDate = DateHelper.convertLongToDate(end)
        val text = "${DateHelper.convertLongToDate(start)}/${DateHelper.convertLongToDate(end)}"
        _state.value = NewsState(
            isFiltered = true,
            topBarFilteredText = text
        )
    }

    fun filterRemoved() {
        DateHelper.fromDate = DateHelper.getTenDaysAgoDate()
        DateHelper.toDate = DateHelper.getCurrentDate()
        _state.value = NewsState(
            isFiltered = false,
            topBarFilteredText = ""
        )
    }
}
