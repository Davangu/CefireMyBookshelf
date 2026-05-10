package com.davant.cefiremybookshelf.screens.addedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary

@Composable
fun AddEditScreen(addEditViewModel: AddEditViewModel) {
    val newBook = addEditViewModel.newBook.observeAsState(true)
    val book = addEditViewModel.book.observeAsState(Book())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Main)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = if (newBook.value) "Add a new Book"
                else "Edit ${book.value.title} data",
                color = Secondary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.value.title,
                onValueChange = { addEditViewModel.updateBook(book.value.copy(title = it)) },
                placeholder = { Text("Title") })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.value.author,
                onValueChange = { addEditViewModel.updateBook(book.value.copy(author = it)) },
                placeholder = { Text("Author") })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.value.year.toString(),
                onValueChange = { addEditViewModel.updateBook(book.value.copy(year = it.toInt())) },
                placeholder = { Text("Year") })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.value.isbn,
                onValueChange = { addEditViewModel.updateBook(book.value.copy(isbn = it)) },
                placeholder = { Text("ISBN") })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.value.cover,
                onValueChange = { addEditViewModel.updateBook(book.value.copy(cover = it)) },
                placeholder = { Text("Cover") })
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Favourite?",
                    fontSize = 18.sp
                )
                Checkbox(
                    checked = book.value.fav,
                    onCheckedChange = { addEditViewModel.updateBook(book.value.copy(fav = it)) })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Have you read it?",
                    fontSize = 18.sp
                )
                Checkbox(
                    checked = book.value.read,
                    onCheckedChange = { addEditViewModel.updateBook(book.value.copy(read = it)) })
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}) {
                Text(
                    text = "SAVE",
                    fontSize = 18.sp
                )
            }
        }
    }
}