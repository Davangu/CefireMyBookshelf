package com.davant.cefiremybookshelf.screens.home

import com.davant.cefiremybookshelf.model.Book


fun filterBookList(bookList: List<Book>, value: Int) =
    when (value) {
        1 -> bookList.filter {
            it.fav
        }
        2 -> bookList.filter {
            it.read
        }
        else -> bookList
    }


fun getNameCleared(oldName: String): String {
    val listOfParts = oldName.split('@')
    return listOfParts[0]
}