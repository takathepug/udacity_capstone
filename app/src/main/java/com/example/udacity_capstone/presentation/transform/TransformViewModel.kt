package com.example.udacity_capstone.presentation.transform

import androidx.lifecycle.*
import com.example.udacity_capstone.domain.usecase.CacheNewLearningMaterialsUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class TransformViewModel(
    private val cacheNewLearningMaterialsUseCase: CacheNewLearningMaterialsUseCase
) : ViewModel() {

    private val _texts = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { _, i ->
            "This is item # $i"
        }
    }

    val texts: LiveData<List<String>> = _texts

    init {
        viewModelScope.launch {
            val res = cacheNewLearningMaterialsUseCase()
            Timber.d(res.toString())
        }
    }

    class Factory(private val cacheNewLearningMaterialsUseCase: CacheNewLearningMaterialsUseCase):
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TransformViewModel(cacheNewLearningMaterialsUseCase) as T
        }
    }
}