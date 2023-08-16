package com.example.learnwithme.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnwithme.domain.entity.Character


@Composable
fun ListCharactersView() {
    MessageList(messages = listOf(Character(name = "Jose")))
}
@Composable
fun MessageList(messages: List<Character>) {
    Column {
        messages.forEach { message ->
            CharacterRow(message)
        }
    }
}

@Composable
fun CharacterRow(character: Character) {
    Text(text = character.name)
}