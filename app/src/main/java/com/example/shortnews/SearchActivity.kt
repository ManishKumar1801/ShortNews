package com.example.shortnews

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortnews.application.MyApplication
import com.example.shortnews.databinding.ActivityMainBinding
import com.example.shortnews.databinding.ActivitySearchBinding
import com.example.shortnews.models.Article
import com.example.shortnews.models.News
import com.example.shortnews.repository.Repository
import com.example.shortnews.viewmodel.MyViewModel
import com.example.shortnews.viewmodel.ViewModelFactory
import com.google.android.material.internal.ViewUtils.showKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchBinding
    lateinit var adapter: MyAdapter
    lateinit var newslist1:List<Article>
    lateinit var viewModel:MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_search)
        val getApi = MyApplication.retrofit.create(NewsApi::class.java)
        val repository = Repository(getApi)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[MyViewModel::class.java]

        binding.search.requestFocus()
        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
            }
            true
        }


    }


    @SuppressLint("SuspiciousIndentation")
    private fun performSearch() {
        newslist1=ArrayList()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val previousDay = dateFormat.format(calendar.time)
        val searchType=binding.search.text.toString()
        viewModel.getNews(searchType,previousDay,"5bc8f27a8cd74ccbbf7a5d678cb7b9cd").
        observe(this){
            newslist1=it.articles
            adapter= MyAdapter(this@SearchActivity ,this@SearchActivity ,newslist1)
            binding.recyclerView.adapter=adapter
            binding.recyclerView.layoutManager= LinearLayoutManager(this@SearchActivity)
        }

    }











}