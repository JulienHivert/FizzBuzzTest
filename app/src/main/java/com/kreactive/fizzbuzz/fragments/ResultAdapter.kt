package com.kreactive.fizzbuzz.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kreactive.fizzbuzz.R
import kotlinx.android.synthetic.main.result_item.view.*

class ResultAdapter: ListAdapter<String, ResultViewHolder>(DIFF_UTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false))

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val items = getItem(position)
        holder.bindData(items)
    }
}

class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindData(string: String) {
        itemView.result_title.text = string
    }
}

private val DIFF_UTILS = object : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}