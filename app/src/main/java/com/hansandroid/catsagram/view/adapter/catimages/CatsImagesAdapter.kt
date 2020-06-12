package com.hansandroid.catsagram.view.adapter.catimages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.CatImageModel

class CatsImagesAdapter(private val imageModels: Array<CatImageModel>) : RecyclerView.Adapter<CatImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_image, parent, false)
        return CatImageViewHolder(view)
    }

    override fun getItemCount(): Int {
       return imageModels.size
    }

    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        val image = imageModels[position]
        holder.bind(image)
    }
}