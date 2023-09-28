package com.example.learnwithme.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnwithme.domain.usecase.CharacterUseCaseInterface
import com.example.learnwithme.domain.entity.Character
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ListCharactersViewModelInterface {
    fun load()
    fun searchThis(text: String)
    fun filterWith(text: String)
    val uiState: StateFlow<CharactersUiState>
}

data class CharactersUiState(
    val originalItems: List<Character> = mutableListOf(),
    val items: List<Character> = mutableListOf(),
    val isLoading: Boolean = false
)

class ListCharactersViewModel(private val useCase: CharacterUseCaseInterface):
    ListCharactersViewModelInterface,
    ViewModel() {
    private var page = 1
    private var hasNextPage = true

    private val _uiState = MutableStateFlow(CharactersUiState(isLoading = true))
    override val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    override fun load() {
        viewModelScope.launch {
            if (hasNextPage) {
                val result = useCase.getNextPageAndCharacters(page)
                delay(3000)
                hasNextPage = result.first
                page += if (hasNextPage) 1 else 0
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        items = it.items + result.second,
                        originalItems = it.items + result.second
                    )
                }
                _uiState.emit(uiState.value)
            }
        }
    }

    override fun searchThis(text: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    items = state.originalItems.filter { it.name.contains(text, ignoreCase = true) }
                )
            }
        }
    }

    override fun filterWith(text: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    items = state.originalItems.filter { it.name.contains(text, ignoreCase = true) }
                )
            }
        }
    }
}