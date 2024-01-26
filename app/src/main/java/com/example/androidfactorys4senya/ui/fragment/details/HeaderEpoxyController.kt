package com.example.androidfactorys4senya.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.databinding.ModelHeaderImageBinding
import com.example.androidfactorys4senya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HeaderEpoxyController(
    private val imageUrls: List<String>
): EpoxyController() {
    override fun buildModels() {
        imageUrls.forEachIndexed { index, url ->
            HeaderImageEpoxyModel(url).id("image_$index").addTo(this)
        }
    }
    inner class HeaderImageEpoxyModel(
        private val  imageUrl: String
    ): ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image) {
        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(imageView)
        }
    }
}