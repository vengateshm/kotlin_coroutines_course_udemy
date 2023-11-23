package dev.vengateshm.kotlincoroutinesudemy.flow_news_ticker.model

import retrofit2.http.GET

interface NewsService {
    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>
}