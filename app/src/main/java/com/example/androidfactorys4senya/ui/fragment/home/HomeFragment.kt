package com.example.androidfactorys4senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.databinding.FragmentHomeBinding
import com.example.androidfactorys4senya.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handle item being clicked - navigate
        val epoxyController = HomeFragmentController { attractionId ->
//            val navDirections = HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
//            navController.navigate(navDirections)
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }
//        binding.epoxyRecyclerView.adapter = epoxyController.adapter
        binding.epoxyRecyclerView.setController(epoxyController)
        binding.epoxyRecyclerView.addItemDecoration( DividerItemDecoration( requireContext(), RecyclerView.VERTICAL ))

        epoxyController.isLoading = true
        activityViewModel.attractionsListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}