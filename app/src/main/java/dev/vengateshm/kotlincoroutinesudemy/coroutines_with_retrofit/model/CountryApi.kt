package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.model

import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("/DevTides/countries/master/countriesV2.json")
    suspend fun getCountryList(): Response<List<Country>>
}