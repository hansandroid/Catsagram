package com.hansandroid.catsagram.view.adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.BreedModel

class BreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mCardView by lazy { view.findViewById(R.id.card) as CardView }
    private val mBreedName by lazy { view.findViewById(R.id.breed_name) as TextView }
    private val mBreedDescription by lazy { view.findViewById(R.id.breed_description) as TextView }
    private val mSocialNeedRate by lazy { view.findViewById(R.id.social_need_rate) as RatingBar }
    private val mChildFriendlyRate by lazy { view.findViewById(R.id.child_friendly_rate) as RatingBar }
    private val mEnergyLevelRate by lazy { view.findViewById(R.id.enerngy_level_rate) as RatingBar }

    fun bind(breedModel: BreedModel, didTap: (breedId: String) -> Unit) {
        mBreedName.text = breedModel.name
        mBreedDescription.text = breedModel.description
        mSocialNeedRate.rating = breedModel.socialNeeds.toFloat()
        mChildFriendlyRate.rating = breedModel.childFriendly.toFloat()
        mEnergyLevelRate.rating = breedModel.energyLevel.toFloat()

        mCardView.setOnClickListener { didTap(breedModel.id) }
    }

}