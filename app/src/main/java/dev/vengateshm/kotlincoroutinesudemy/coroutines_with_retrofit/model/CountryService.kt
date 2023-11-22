package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryService {
    private val BASE_URL = "https://raw.githubusercontent.com"

    fun getCountryApi(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }
}