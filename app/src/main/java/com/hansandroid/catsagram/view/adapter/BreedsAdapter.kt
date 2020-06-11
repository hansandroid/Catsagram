package com.hansandroid.catsagram.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.BreedModel

class BreedsAdapter(private val breeds: Array<BreedModel>,
                    private val didTap: (breedId: String) -> Unit) : RecyclerView.Adapter<BreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breeds[position]
        holder.bind(breed, didTap)
    }
}