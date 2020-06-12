package com.hansandroid.catsagram.view.adapter.catimages

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.CatImageModel

class CatImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mImageView by lazy { view.findViewById(R.id.image) as ImageView }

    fun bind(catImageModel: CatImageModel) {
        Glide.with(mImageView.context).load(catImageModel.url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(mImageView)
    }

}