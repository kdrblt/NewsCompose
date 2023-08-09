package com.example.newscompose.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.newscompose.domain.dto.ArticleDto
import com.google.gson.Gson

class CustomNavType : NavType<ArticleDto>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArticleDto? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ArticleDto {
        return Gson().fromJson(value, ArticleDto::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ArticleDto) {
        bundle.putParcelable(key, value)
    }
}
