package com.hansandroid.catsagram.view.adapter.breeds

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.BreedModel
import java.util.*
import kotlin.collections.ArrayList

class BreedsAdapter(private val breeds: Array<BreedModel>,
                    private val didTap: (breedId: String, breedName: String) -> Unit) : RecyclerView.Adapter<BreedViewHolder>(), Filterable {

    private var filteredBreeds = breeds.toList() as ArrayList<BreedModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredBreeds.size
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = filteredBreeds[position]
        holder.bind(breed, didTap)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filteredBreeds = if (charString.isEmpty()) {
                    breeds.toList() as ArrayList<BreedModel>
                } else {
                    val filteredList: ArrayList<BreedModel> = ArrayList()
                    for (breed in breeds) {
                        if (breed.name.toLowerCase(Locale.ROOT)
                                .contains(charString)
                        ) {
                            filteredList.add(breed)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredBreeds
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                filteredBreeds = filterResults.values as ArrayList<BreedModel>
                notifyDataSetChanged()
            }
        }
    }
}