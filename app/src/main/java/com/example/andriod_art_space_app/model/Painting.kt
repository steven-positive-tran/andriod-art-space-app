package com.example.andriod_art_space_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Painting(
    @StringRes val title: Int,
    @StringRes val artistName: Int,
    @StringRes val year: Int,
    @DrawableRes val imageResource: Int,
)
