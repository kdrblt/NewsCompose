package com.example.newscompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newscompose.data.model.local.ArticleDbItem

@Database(entities = [ArticleDbItem::class], version = 1, exportSchema = false)
abstract class ArticleListRoomDB : RoomDatabase() {
    abstract fun articleListDAO(): ArticleListDAO
}
