package com.davant.cefiremybookshelf.data.openlibrary.covers

import com.davant.cefiremybookshelf.domain.model.Cover
import com.davant.cefiremybookshelf.domain.repository.CoversRepository

class OpenLibraryRepository(val apiService:CoverOLService): CoversRepository {
    override val path = "https://covers.openlibrary.org/b/id/"
    override val extension = ".jpg"
    override suspend fun getCoverByIsbn(isbn: String): Cover {
        return apiService.getCoverByIsbn(isbn).toCover()
    }
}