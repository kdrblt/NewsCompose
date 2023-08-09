package com.example.newscompose.data.api

import com.example.newscompose.common.Constants.NEWS_API_KEY
import com.example.newscompose.common.DateHelper
import com.example.newscompose.common.PagingHelper
import com.example.newscompose.data.model.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteService {

    @GET(EVERYTHING)
    suspend fun getRemoteNews(
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("q") keyword: String = KEYWORD_FOOTBALL,
        @Query("sortBy") sortBy: String = SORTBY_PUBLISHEDAT,
        @Query("page") page: Int = PagingHelper.pageIndex,
        @Query("pageSize") pageSize: Int = PagingHelper.pageSize,
        @Query("from") from: String = DateHelper.fromDate,
        @Query("to") to: String = DateHelper.toDate
    ): NewsResponse

    companion object {
        private const val EVERYTHING = "everything"
        private const val KEYWORD_FOOTBALL = "football"
        private const val SORTBY_PUBLISHEDAT = "publishedAt"
    }
}
