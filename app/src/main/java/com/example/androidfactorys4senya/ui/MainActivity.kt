package com.example.androidfactorys4senya.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidfactorys4senya.R
import com.example.androidfactorys4senya.data.Attraction
import com.example.androidfactorys4senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    val attractionList: List<Attraction> by lazy {
        parseAttractions()
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun parseAttractions():  List<Attraction> {
        val textFromFile = resources.openRawResource(R.raw.croatia)
            .bufferedReader().use { it.readText() }
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//        val type = Types.newParameterizedType(
//            List::class.java,
//            Attraction::class.java
//        )
//        val adapter: JsonAdapter<List<Attraction>> = moshi.adapter(type)
//        return adapter.fromJson(textFromFile) as? ArrayList<Attraction> ?: ArrayList()
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions //as ArrayList<Attraction>

    }

}