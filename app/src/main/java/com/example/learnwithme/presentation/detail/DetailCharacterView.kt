package com.example.learnwithme.presentation.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.learnwithme.presentation.list.CharacterRow
import com.example.learnwithme.presentation.list.CustomProgressIndicator
import com.example.learnwithme.presentation.list.InfiniteScroll

@Composable
fun DetailCharactersView(character: String
) {
    Text(character)
}