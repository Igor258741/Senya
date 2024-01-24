package com.example.androidfactorys4senya.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
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
import androidx.lifecycle.Lifecycle
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.databinding.FragmentActtractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment: BaseFragment() {
    private var _binding: FragmentActtractionDetailBinding? = null
    private val binding get() = _binding!!
//    private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActtractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.titleTextView.text = attraction.title
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.numberOfFactsTextView.text = "${attraction.facts.size} Facts"
            binding.descriptionTextView.text = attraction.description
            Picasso.get().load(attraction.image_urls[0]).into(binding.imageView)
            binding.numberOfFactsTextView.setOnClickListener {

                val stringBuilder = StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append("\u2022 $it")
                    stringBuilder.append("\n\n")
                }
                val message = stringBuilder.toString()
//            AlertDialog.Builder(requireContext(), R.style.MyDialog)
                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("Ok", object :DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
//                        dialog?.dismiss()
                        }
                    })
                    .setNegativeButton("No!") { dialog, which ->
//                    dialog.dismiss()
                    }
//                .setCancelable(false)
                    .show()
            }
        }

        requireActivity().addMenuProvider( object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_attraction_detail, menu)
            }
            @SuppressLint("QueryPermissionsNeeded")
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val attraction = activityViewModel.selectedAttractionLiveData.value!!
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                        activityViewModel.locationSelectedLiveData.postValue(attraction)
/*                        //https://developers.google.com/maps/documentation/urls/android-intents?hl=ru#display-a-map
                        // Create a Uri from an intent string. Use the result to create an Intent.
                        val gmmIntentUri = Uri.parse(
                            "geo:${attraction.location.latitude},${attraction.location.longitude}?z=15" +
//                                    "&q=${attraction.title}" +
                                    "")
                        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        // Make the Intent explicit by setting the Google Maps package
                        mapIntent.setPackage("com.google.android.apps.maps")
                        // Attempt to start an activity that can handle the Intent
                        mapIntent.resolveActivity(requireContext().packageManager)?.let {
                            startActivity(mapIntent)
                        }*/
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