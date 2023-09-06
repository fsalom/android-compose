package com.example.learnwithme.presentation.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.learnwithme.presentation.list.CustomProgressIndicator

@Composable
fun DetailCharactersView(
    viewModel: DetailCharactersViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CustomProgressIndicator()
        viewModel.load()
    }

    uiState.character?.let {
        Text(it.name)
    }
}