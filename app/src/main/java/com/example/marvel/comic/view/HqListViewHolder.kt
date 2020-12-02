package com.example.marvel.comic.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.comic.model.HqModel
import com.squareup.picasso.Picasso

class HqListViewHolder (private val view: View): RecyclerView.ViewHolder(view) {
    private val number = view.findViewById<TextView>(R.id.txtHqNumber)
    private val image = view.findViewById<ImageView>(R.id.imgHqs)

    @SuppressLint("SetTextI18n")
    fun bind(comicModel: HqModel) {
        number.text = "# ${comicModel.id}"
        val imagePath = comicModel.thumbnail?.getImagePath()
        Picasso.get().load(imagePath).into(image)
    }
}