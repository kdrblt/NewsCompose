package com.example.newscompose.ui.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.newscompose.R

object Texts {

    @Composable
    operator fun invoke(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        fontFamily: FontFamily? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        maxLines: Int = Int.MAX_VALUE
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            maxLines = maxLines
        )
    }

    @Composable
    fun TitleBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = colorResource(id = R.color.text_gray),
        fontSize: TextUnit = 16.sp,
        fontWeight: FontWeight? = FontWeight.Bold,
        fontFamily: FontFamily? = RobotoFontFamily,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = 20.sp,
        overflow: TextOverflow = TextOverflow.Ellipsis,
        maxLines: Int = Int.MAX_VALUE
    ) = Texts(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        maxLines = maxLines
    )

    @Composable
    fun BodyRegular(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = colorResource(id = R.color.text_gray),
        fontSize: TextUnit = 14.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        fontFamily: FontFamily? = RobotoFontFamily,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = 17.sp,
        overflow: TextOverflow = TextOverflow.Ellipsis,
        maxLines: Int = Int.MAX_VALUE
    ) = Texts(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        maxLines = maxLines
    )
}
