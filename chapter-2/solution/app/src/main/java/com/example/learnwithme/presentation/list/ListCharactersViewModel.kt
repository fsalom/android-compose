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
    val uiState: StateFlow<CharactersUiState>
}

data class CharactersUiState(
    val items: List<Character> = mutableListOf(),
    val isLoading: Boolean
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
                hasNextPage = result.first
                page += if (hasNextPage) 1 else 0
                delay(3000)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        items = it.items + result.second,
                    )
                }
            }
        }
    }
}