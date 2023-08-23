package com.example.learnwithme.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnwithme.domain.usecase.CharacterUseCaseInterface
import com.example.learnwithme.domain.entity.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ListCharactersViewModelInterface {
    fun load()
    val uiState: StateFlow<CharactersUiState>
    val isRefresh: Boolean
}

data class CharactersUiState(
    val items: List<Character> = mutableListOf(),
    val isLoading: Boolean = false
)

class ListCharactersViewModel(private val useCase: CharacterUseCaseInterface):
    ListCharactersViewModelInterface,
    ViewModel() {
    private var page = 1
    override val isRefresh: Boolean = false

    private val _uiState = MutableStateFlow(CharactersUiState(isLoading = true))
    override val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    override fun load() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            _uiState.emit(uiState.value)
            val result = useCase.getNextPageAndCharacters(page)
            page += if (result.first) 1 else 0
            _uiState.update {
                it.copy(
                    isLoading = false,
                    items = it.items + result.second
                )
            }
            _uiState.emit(uiState.value)
        }
    }
}