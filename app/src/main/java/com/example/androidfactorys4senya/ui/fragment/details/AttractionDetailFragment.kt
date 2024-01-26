package com.example.androidfactorys4senya.ui.fragment.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.databinding.FragmentActtractionDetailBinding
import com.example.androidfactorys4senya.ui.fragment.BaseFragment

class AttractionDetailFragment: BaseFragment() {
    private var _binding: FragmentActtractionDetailBinding? = null
    private val binding get() = _binding!!
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
            binding.headerEpoxyRecyclerView.setControllerAndBuildModels(
                HeaderEpoxyController( attraction.image_urls)
            )
            LinearSnapHelper().attachToRecyclerView(binding.headerEpoxyRecyclerView)
            binding.indicator.attachToRecyclerView(binding.headerEpoxyRecyclerView)

            var isGridMode: Boolean = binding.contentEpoxyRecyclerView.layoutManager is GridLayoutManager
            val contentEpoxyController = ContentEpoxyController(attraction)
            contentEpoxyController.isGidMode = isGridMode
            contentEpoxyController.onChangeLayoutCallback ={
                if (isGridMode) {
                    binding.contentEpoxyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                } else {
                    binding.contentEpoxyRecyclerView.layoutManager = GridLayoutManager( requireContext(), 2)
                }
                isGridMode = !isGridMode
                contentEpoxyController.isGidMode = isGridMode
                contentEpoxyController.requestModelBuild()
            }
            binding.contentEpoxyRecyclerView.setControllerAndBuildModels(contentEpoxyController)

        }
        requireActivity().addMenuProvider( object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_attraction_detail, menu)
            }
            @SuppressLint("QueryPermissionsNeeded")
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                        activityViewModel.locationSelectedLiveData.postValue(attraction)
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