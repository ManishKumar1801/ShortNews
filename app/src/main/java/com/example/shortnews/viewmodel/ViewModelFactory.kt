package com.example.shortnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.shortnews.repository.Repository

class ViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MyViewModel(repository) as T
    }
}