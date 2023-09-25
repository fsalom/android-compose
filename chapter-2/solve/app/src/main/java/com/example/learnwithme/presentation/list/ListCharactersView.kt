package com.example.learnwithme.presentation.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.learnwithme.presentation.customview.CustomProgressIndicator
import com.example.learnwithme.presentation.list.customview.CharacterRow

@Composable
fun ListCharactersView(viewModel: ListCharactersViewModelInterface) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CustomProgressIndicator()
        viewModel.load()
    }

    if (uiState.items.isNotEmpty()) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(uiState.items.size) { index ->
                CharacterRow(uiState.items[index])
            }
        }
    }
}