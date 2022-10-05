package com.bignerdranch.anroid.geoquiz

import androidx.annotation.StringRes

data class Question (@StringRes val textResId: Int,
                     val answer: Boolean)