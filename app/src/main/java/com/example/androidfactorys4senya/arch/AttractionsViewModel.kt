package com.example.androidfactorys4senya.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfactorys4senya.data.Attraction

class AttractionsViewModel: ViewModel() {
    private val repository = AttractionRepository()
    val attractionsListLiveData = MutableLiveData<ArrayList<Attraction>>()
    val selectedAttractionLiveData = MutableLiveData<Attraction>()
    val locationSelectedLiveData =  MutableLiveData<Attraction>()
    fun init (context: Context) {
        val attractionsList = repository.parseAttractions(context)
        attractionsListLiveData.postValue(attractionsList)
    }
    fun onAttractionSelected (attractionId: String) {
        val attraction = attractionsListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }
}