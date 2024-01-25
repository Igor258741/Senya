package com.example.androidfactorys4senya.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.data.Attraction
import com.example.androidfactorys4senya.databinding.ViewHolderAttractionBinding
import com.example.androidfactorys4senya.ui.epoxy.LoadingEpoxyModel
import com.example.androidfactorys4senya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(
    private val onClickedCallback: (String) -> Unit
): EpoxyController(){
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            // todo show loading state
            LoadingEpoxyModel().id("Loading state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            // todo show empty state
            return
        }
        attractions.forEach { attraction ->
            AttractionEpoxyModel( attraction, onClickedCallback)
                .id( attraction.id)
                .addTo( this)
        }
    }
    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ): ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            monthsToVisitTextView.text = attraction.months_to_visit
            //image via Picasso
            Picasso.get().load(attraction.image_urls[0]).into(headerImageView)
            root.setOnClickListener {
                onClicked (attraction.id)
            }
        }
    }


}