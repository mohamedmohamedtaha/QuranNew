package com.mohamedtaha.imagine.util

import android.view.View

interface ClickListener<T> {
    fun onClick(view: View?, position: T)
}