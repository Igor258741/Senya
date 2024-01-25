package com.example.androidfactorys4senya.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfactorys4senya.data.Attraction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionsViewModel: ViewModel() {
    private val repository = AttractionRepository()
    // HomeFragment
    val attractionsListLiveData = MutableLiveData<ArrayList<Attraction>>()
    // AttractionDetailFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()
    // MainActivity
    val locationSelectedLiveData =  MutableLiveData<Attraction>()
    fun init (context: Context) {
//        val attractionsList = repository.parseAttractions(context)
//        attractionsListLiveData.postValue(attractionsList)
        viewModelScope.launch {
            delay(5000)
            val attractionsList = repository.parseAttractions(context)
            attractionsListLiveData.postValue(attractionsList)
        }
    }
    fun onAttractionSelected (attractionId: String) {
        val attraction = attractionsListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }
}