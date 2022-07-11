package com.kreactive.fizzbuzz.fragments

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.kreactive.fizzbuzz.*
import com.kreactive.fizzbuzz.exception.getErrorLabel
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment: Fragment(R.layout.settings_fragment) {

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
        const val TAG = "SettingFragment"
    }

    private lateinit var userSettings: UserSetting

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        mainViewModel.errorNumberData.observe(viewLifecycleOwner) {
            handleInputLayoutException(first_value, it)
            handleInputLayoutException(second_value, it)
            handleInputLayoutException(limit_valued, it)
        }

        mainViewModel.errorWordLiveData.observe(viewLifecycleOwner) {
            handleInputLayoutException(first_word, it)
            handleInputLayoutException(second_word, it)
        }

        mainViewModel.result.observe(viewLifecycleOwner) {
            openResultFragment(it)
        }
    }

    private fun openResultFragment(result: List<String>) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, ResultFragment.newInstance(result), ResultFragment.TAG)
            addToBackStack(ResultFragment.TAG)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            hide(this@SettingsFragment)
            commit()
        }
    }

    private fun bindView() {
        first_word_edit.setOnEditorActionListener(onEditorActionListener)
        second_value_edit.setOnEditorActionListener(onEditorActionListener)
        first_word_edit.setOnEditorActionListener(onEditorActionListener)
        second_word_edit.setOnEditorActionListener(onEditorActionListener)
        limit_value_edit.setOnEditorActionListener(onEditorActionListener)
    }

    private fun displayStartButton() {
        statTest.show()
        statTest.setOnClickListener {
            mainViewModel.startTest(userSettings)
        }
    }

    private val onEditorActionListener = TextView.OnEditorActionListener { view, actionId, _ ->
        val val1 = first_value_edit.text.toString().trim()
        val val2 = second_value_edit.text.toString().trim()
        val word1 = first_word_edit.text.toString().trim()
        val word2 = second_word_edit.text.toString().trim()
        val limit = limit_value_edit.text.toString().trim()
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            userSettings = UserSetting(val1.toInt(), val2.toInt(), word1, word2, limit.toInt())
            view.hideKeyboard()
            displayStartButton()
        }
        true
    }

    private fun handleInputLayoutException(inputLayout: TextInputLayout?, error: Exception?) {
        if (inputLayout != null) {
            val errorToDisplay = getErrorLabel(inputLayout.context, error)
            inputLayout.error = errorToDisplay
            inputLayout.isErrorEnabled = errorToDisplay != null
        }
    }
}