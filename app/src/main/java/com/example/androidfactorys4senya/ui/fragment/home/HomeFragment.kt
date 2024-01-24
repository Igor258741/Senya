package com.example.androidfactorys4senya.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
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

        val homeAdapter = HomeFragmentAdapter {
            // handle item being clicked - navigate

        }
        binding.recyclerView.adapter = homeAdapter
        homeAdapter.setData(attractions)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(), RecyclerView.VERTICAL
            ))
//        navController.navigateUp()
//        findNavController().navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}