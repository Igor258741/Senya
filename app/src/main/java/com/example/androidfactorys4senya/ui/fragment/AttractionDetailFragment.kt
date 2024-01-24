package com.example.androidfactorys4senya.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfactorys4senya.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)

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
        requireActivity().addMenuProvider( object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_attraction_detail, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        // Create a Uri from an intent string. Use the result to create an Intent.
                        val gmmIntentUri = Uri.parse(
                            "geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
                        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        // Make the Intent explicit by setting the Google Maps package
                        mapIntent.setPackage("com.google.android.apps.maps")
                        // Attempt to start an activity that can handle the Intent
                        startActivity(mapIntent)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}