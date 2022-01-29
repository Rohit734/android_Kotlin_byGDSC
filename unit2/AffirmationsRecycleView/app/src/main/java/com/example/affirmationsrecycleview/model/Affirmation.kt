package com.example.affirmationsrecycleview.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResourcesId: Int,
    @DrawableRes val imageResourcesId: Int
)