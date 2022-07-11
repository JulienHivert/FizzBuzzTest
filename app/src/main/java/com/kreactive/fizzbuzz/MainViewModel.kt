package com.kreactive.fizzbuzz

import android.app.Application
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kreactive.fizzbuzz.exception.InvalidFieldException
import com.kreactive.fizzbuzz.exception.MandatoryFieldException
import java.lang.Exception

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val results = mutableListOf<String>()

    private val _errorWordViewModel : MutableLiveData<Exception?> = MutableLiveData()
    val errorWordLiveData: LiveData<Exception?>
    get() = _errorWordViewModel

    private val _errorNumberViewModel : MutableLiveData<Exception?> = MutableLiveData()
    val errorNumberData: LiveData<Exception?>
        get() = _errorNumberViewModel

    private val _result : MutableLiveData<List<String>> = MutableLiveData()
    val result: LiveData<List<String>>
        get() = _result

    fun startTest(userSetting: UserSetting) {
        if (isValidValue(userSetting.val1.toString()) && isValidValue(userSetting.val2.toString()) && isValidWord(
                userSetting.word1
            ) && isValidWord(userSetting.word2) && isValidValue(userSetting.limit.toString())
        ) {
            calculate(userSetting)
        } else {
            Log.i("plouf", "tmp")
        }
    }

    private fun calculate(userSetting: UserSetting) {
        repeat((0..userSetting.limit).count()) {
            val result: String = when {
                calculateMultiples(it, userSetting.val1) && calculateMultiples(it, userSetting.val2) -> "${userSetting.word1}${userSetting.word2}"
                calculateMultiples(it, userSetting.val1) -> userSetting.word1
                calculateMultiples(it, userSetting.val2) -> userSetting.word2
                else -> "$it"
            }
            results.add(result)
        }
        _result.postValue(results)
    }

    private fun calculateMultiples(currentValue: Int, valueSetByUser: Int): Boolean =
        currentValue % valueSetByUser == 0

    private fun isValidValue(number: String): Boolean {
            val exception = when {
                number.isBlank() -> MandatoryFieldException()
                !number.isDigitsOnly() -> InvalidFieldException()
                else -> null
            }
            _errorNumberViewModel.postValue(exception)
            return exception == null
        }

    private fun isValidWord(word: String): Boolean {
        val exception = when {
            word.isBlank() -> MandatoryFieldException()
            word.isDigitsOnly() -> InvalidFieldException()
            else -> null
        }
        _errorWordViewModel.postValue(exception)
        return exception == null
    }
}