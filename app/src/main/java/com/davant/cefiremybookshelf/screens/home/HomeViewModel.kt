package com.davant.cefiremybookshelf.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.domain.repository.BooksRepository

class HomeViewModel(private val repository: BooksRepository): ViewModel() {
    private val _bookListFull = MutableLiveData<List<Book>>()

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList:LiveData<List<Book>> = _bookList

    private val _contentIndex = MutableLiveData<Int>()
    val contentIndex:LiveData<Int> = _contentIndex

    init {
        _bookListFull.value = repository.getAllBooks()
        _bookList.value = _bookListFull.value?.let { filterBookList(it, 0) }
    }

    fun onContentIndexChange(index:Int){
        _contentIndex.value = index
        _bookList.value = _bookListFull.value?.let { filterBookList(it, index) }
    }

    fun getAllBooks(){
        _bookListFull.value = repository.getAllBooks()
    }
}