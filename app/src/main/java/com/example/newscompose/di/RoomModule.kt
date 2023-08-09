package com.example.newscompose.di

import android.content.Context
import androidx.room.Room
import com.example.newscompose.data.source.local.ArticleListDAO
import com.example.newscompose.data.source.local.ArticleListRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideArticleListRoomDB(@ApplicationContext appContext: Context): ArticleListRoomDB =
        Room.databaseBuilder(
            appContext,
            ArticleListRoomDB::class.java,
            "articles.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleListDAP(articleListRoomDB: ArticleListRoomDB): ArticleListDAO =
        articleListRoomDB.articleListDAO()
}
