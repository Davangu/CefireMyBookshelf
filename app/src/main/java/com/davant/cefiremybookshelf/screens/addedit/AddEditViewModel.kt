package com.davant.cefiremybookshelf.screens.addedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davant.cefiremybookshelf.domain.model.Book

class AddEditViewModel(private val inBook: Book) : ViewModel() {
    private val _book = MutableLiveData(inBook)
    val book: LiveData<Book> = _book

    private val _newBook = MutableLiveData(true)
    val newBook: LiveData<Boolean> = _newBook

    init {
        _newBook.value = inBook.title.isEmpty()
    }

    fun updateBook(book: Book) {
        _book.value = book
    }
}