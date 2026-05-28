package com.davant.cefiremybookshelf.domain.repository

import com.davant.cefiremybookshelf.domain.model.Cover

interface CoversRepository {
    val path: String
    val extension:String

    suspend fun getCoverByIsbn(isbn:String):Cover
}