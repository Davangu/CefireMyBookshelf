package com.davant.cefiremybookshelf.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.domain.model.Preferences
import com.davant.cefiremybookshelf.domain.repository.BooksRepository
import com.davant.cefiremybookshelf.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: BooksRepository,
    private val preferencesRepository: PreferencesRepository,
    val userName: String,
    private val userId: String,
    val goToAddEditScreen: (Book) -> Unit,
    val goBack: () -> Unit,
    val goToPreferencesScreen: (Preferences) -> Unit
) : ViewModel() {

    private val _contentIndex = MutableStateFlow(0)
    val contentIndex: StateFlow<Int> = _contentIndex

    val bookList: StateFlow<List<Book>> = repository.getBooks(userId)
        .combine(_contentIndex) { books, index ->
            filterBookList(books, index)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val preferences: StateFlow<Preferences> =
        preferencesRepository.getPreferencesByUser(userId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Preferences(id = userId)
            )

    fun onContentIndexChange(index: Int) {
        _contentIndex.value = index
    }
}
