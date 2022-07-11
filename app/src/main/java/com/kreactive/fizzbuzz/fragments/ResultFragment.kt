package com.kreactive.fizzbuzz.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kreactive.fizzbuzz.R
import kotlinx.android.synthetic.main.result_fragment.*

/**
 * Created by JulienHivert on 11/07/2022
 */
class ResultFragment: Fragment(R.layout.result_fragment) {

    companion object {
        fun newInstance(resultList: List<String>) = ResultFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(RESULT_ARGS, ArrayList(resultList))
            }
        }

        const val TAG = "ResultFragment"
        const val RESULT_ARGS = "RESULT_ARGS"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {
        val listToDisplay = requireArguments().getStringArrayList(RESULT_ARGS)
        val adapter = ResultAdapter()
        result_rv.adapter = adapter
        adapter.submitList(listToDisplay)
    }
}