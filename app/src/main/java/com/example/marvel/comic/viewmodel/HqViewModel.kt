package com.example.marvel.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvel.comic.model.HqModel
import com.example.marvel.comic.repository.HqRepository
import kotlinx.coroutines.Dispatchers

class HqViewModel (
        private val _repository: HqRepository): ViewModel() {
    private var _Hqs: List<HqModel> = listOf()
    private var _totalPages: Int = 0
    private var _offset: Int = 0
    private var _count: Int = 0

    fun getList() = liveData(Dispatchers.IO) {
        val response = _repository.getHqs()

        _count = response.data.count
        if (response.data.total != 0) {
            _totalPages = response.data.total / _count
        } else {
            _totalPages = 0
        }
        _Hqs = response.data.results
        emit(response.data.results)
    }

    fun nextPage() = liveData(Dispatchers.IO) {
        if (_offset.plus(_count) <= _totalPages) {
            _offset = _offset.plus(_count)
            val response = _repository.getHqs(_offset)
            emit(response.data.results)
        }
    }
    class HqViewModelFactory(private val _repository: HqRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HqViewModel(_repository) as T
        }
    }
}