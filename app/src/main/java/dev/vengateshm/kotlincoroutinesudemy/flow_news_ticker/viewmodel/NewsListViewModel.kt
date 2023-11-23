package dev.vengateshm.kotlincoroutinesudemy.flow_news_ticker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.vengateshm.kotlincoroutinesudemy.flow_news_ticker.model.NewsRepository

class NewsListViewModel : ViewModel() {
    val newsArticle = NewsRepository().getNewsArticles().asLiveData()
}