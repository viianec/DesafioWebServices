package com.example.marvel.comic.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.comic.model.HqModel


class HqListAdapter(private val dataset: List<HqModel>, private val listener: (HqModel) -> Unit):
        RecyclerView.Adapter<HqListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HqListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_hqs_list_item, parent, false)
        return HqListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HqListViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = dataset.size
    }
