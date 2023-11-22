package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.kotlincoroutinesudemy.R
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.viewmodel.CountryListViewModel

class CountryListActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryListViewModel
    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        viewModel = ViewModelProvider(this)[CountryListViewModel::class.java]

        findViewById<RecyclerView>(R.id.rvCountryList).apply {
            layoutManager = LinearLayoutManager(this@CountryListActivity)
            adapter = countryListAdapter
        }

        viewModel.refresh()
        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.countryList.observe(this) { countryList ->
            if (countryList.isNullOrEmpty().not()) {
                findViewById<RecyclerView>(R.id.rvCountryList).visibility = View.VISIBLE
                countryListAdapter.updateCountries(countryList)
            }
        }
        viewModel.countryListError.observe(this) { error ->
            if (error.isNullOrEmpty().not()) {
                findViewById<TextView>(R.id.tvError).text = error
            }
        }
        viewModel.isLoading.observe(this) { isLoadingN ->
            isLoadingN?.let { isLoading ->
                if (isLoading) {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                    findViewById<RecyclerView>(R.id.rvCountryList).visibility = View.GONE
                    findViewById<TextView>(R.id.tvError).visibility = View.GONE
                } else {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
            }
        }
    }
}