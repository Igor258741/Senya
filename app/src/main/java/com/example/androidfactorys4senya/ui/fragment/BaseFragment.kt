package com.example.androidfactorys4senya.ui.fragment

import androidx.fragment.app.Fragment
import com.example.androidfactorys4senya.data.Attraction
import com.example.androidfactorys4senya.ui.MainActivity

abstract class BaseFragment: Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }
    protected val attractions: List<Attraction>
        get() = (activity as MainActivity).attractionList
}