package com.my.remotemediator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import androidx.paging.map
import com.my.remotemediator.data.toVo
import com.my.remotemediator.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val newsFlow = repository.getNewsStream("us", "9b974abaaf3f4bdda40aff4557ca737a")
        .flow
        .map { data ->
            data.map { entity ->
                entity.toVo()
            }
        }
        .cachedIn(viewModelScope)
}
