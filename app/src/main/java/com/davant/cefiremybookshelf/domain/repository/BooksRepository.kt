package com.davant.cefiremybookshelf.domain.repository

import com.davant.cefiremybookshelf.domain.model.Book

interface BooksRepository {
    fun getAllBooks():List<Book>
}