package com.example.newscompose.di

import com.example.newscompose.data.api.NewsRemoteService
import com.example.newscompose.data.repository.NewsLocalRepositoryImpl
import com.example.newscompose.data.repository.NewsRemoteRepositoryImpl
import com.example.newscompose.data.source.local.ArticleListDAO
import com.example.newscompose.domain.repository.NewsLocalRepository
import com.example.newscompose.domain.repository.NewsRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRemoteRepository(
        newsRemoteService: NewsRemoteService,
        newsLocalRepository: NewsLocalRepository
    ): NewsRemoteRepository {
        return NewsRemoteRepositoryImpl(newsRemoteService, newsLocalRepository)
    }

    @Provides
    @Singleton
    fun provideNewsLocalRepository(
        ioDispatcher: CoroutineContext,
        articleListDAO: ArticleListDAO
    ): NewsLocalRepository {
        return NewsLocalRepositoryImpl(
            ioDispatcher,
            articleListDAO
        )
    }
}
