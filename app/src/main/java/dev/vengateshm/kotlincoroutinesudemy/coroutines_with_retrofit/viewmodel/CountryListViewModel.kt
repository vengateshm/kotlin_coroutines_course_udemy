package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.model.Country
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.model.CountryService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel : ViewModel() {
    private val _countryList = MutableLiveData<List<Country>>()
    val countryList: LiveData<List<Country>> = _countryList

    private val _countryListError = MutableLiveData<String?>()
    val countryListError: LiveData<String?> = _countryListError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    fun refresh() {
        getCountryListRemote()
    }

    private fun getCountryListLocal() {
        _isLoading.value = true
        val countryList = getSampleCountryList()
        _countryList.value = countryList
        _countryListError.value = null
        _isLoading.value = false
    }

    private fun getCountryListRemote() {
        _isLoading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CountryService.getCountryApi().getCountryList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _countryList.value = response.body()
                    _countryListError.value = null
                    _isLoading.value = false
                } else {
                    onError("Error ${response.message()}")
                }
            }
        }
    }

    private fun getSampleCountryList(): List<Country> {
        return buildList {
            add(Country(countryName = "Country 1", capital = "Capital 1", flag = ""))
            add(Country(countryName = "Country 2", capital = "Capital 2", flag = ""))
            add(Country(countryName = "Country 3", capital = "Capital 3", flag = ""))
            add(Country(countryName = "Country 4", capital = "Capital 4", flag = ""))
            add(Country(countryName = "Country 5", capital = "Capital 5", flag = ""))
        }
    }

    private fun onError(message: String) {
        _countryListError.value = message
        _isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}