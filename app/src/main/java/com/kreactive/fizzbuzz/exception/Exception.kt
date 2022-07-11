package com.kreactive.fizzbuzz.exception

import android.content.Context
import com.kreactive.fizzbuzz.R

class MandatoryFieldException : Exception()

class InvalidFieldException : Exception()

fun getErrorLabel(context: Context, error: Exception?): String? {
    with(context) {
        return when (error) {
            is MandatoryFieldException -> getString(R.string.subscribeFieldErrorMandatoryField)
            is InvalidFieldException -> getString(R.string.subscribeFieldErrorInvalidField)
            else -> null
        }
    }
}