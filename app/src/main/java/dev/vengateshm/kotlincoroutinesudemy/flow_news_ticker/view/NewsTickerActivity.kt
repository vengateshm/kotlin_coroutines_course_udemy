package dev.vengateshm.kotlincoroutinesudemy.flow_news_ticker.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.kotlincoroutinesudemy.R
import dev.vengateshm.kotlincoroutinesudemy.flow_news_ticker.viewmodel.NewsListViewModel

class NewsTickerActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsListViewModel
    private val newsListAdapter = NewsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_ticker)

        viewModel = ViewModelProvider(this)[NewsListViewModel::class.java]

        findViewById<RecyclerView>(R.id.newsList).apply {
            layoutManager = LinearLayoutManager(this@NewsTickerActivity)
            adapter = newsListAdapter
        }

        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.newsArticle.observe(this) {
            findViewById<ProgressBar>(R.id.loading_view).visibility = View.GONE
            findViewById<RecyclerView>(R.id.newsList).apply {
                visibility = View.VISIBLE
                newsListAdapter.onAddNewsItem(it)
                smoothScrollToPosition(0)
            }
        }
    }
}