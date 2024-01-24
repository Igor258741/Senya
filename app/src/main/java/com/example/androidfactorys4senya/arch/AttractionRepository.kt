package com.example.androidfactorys4senya.arch

import android.content.Context
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.data.Attraction
import com.example.androidfactorys4senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionRepository {
    // networking
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    fun parseAttractions(context: Context):  List<Attraction> {
        val textFromFile = context.resources.openRawResource(R.raw.croatia)
            .bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions ?: listOf()
//        val type = Types.newParameterizedType(
//            List::class.java,
//            Attraction::class.java
//        )
//        val adapter: JsonAdapter<List<Attraction>> = moshi.adapter(type)
    }
}