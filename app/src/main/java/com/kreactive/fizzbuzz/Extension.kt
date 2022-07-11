package com.kreactive.fizzbuzz

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by JulienHivert on 11/07/2022
 */

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.show(){
    this.visibility = View.VISIBLE
}