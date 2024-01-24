package com.example.androidfactorys4senya.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfactorys4senya.data.Attraction
import com.example.androidfactorys4senya.databinding.FragmentActtractionDetailBinding
import com.example.androidfactorys4senya.databinding.FragmentHomeBinding
import com.example.androidfactorys4senya.ui.fragment.home.HomeFragmentAdapter
import com.example.androidfactorys4senya.ui.fragment.home.HomeFragmentDirections
import com.squareup.picasso.Picasso

class AttractionDetailFragment: BaseFragment() {
    private var _binding: FragmentActtractionDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActtractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTextView.text = attraction.title
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size} Facts"
        binding.descriptionTextView.text = attraction.description
        Picasso.get().load(attraction.image_urls[0]).into(binding.imageView)
        binding.numberOfFactsTextView.setOnClickListener {
            // todo
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}