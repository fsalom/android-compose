package com.example.learnwithme.presentation.list
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.learnwithme.domain.entity.Character
import kotlinx.coroutines.delay

@Composable
fun ListCharactersView(viewModel: ListCharactersViewModelInterface) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CustomProgressIndicator()
        viewModel.load()
    }

    InfiniteScroll(itemCount = uiState.items.size,
        loadMoreItems = {
            viewModel.load()
        }) {
            CharacterRow(uiState.items[it.first])
            if (it.second) {
                CustomProgressIndicator()
            }
        }
}

@Composable
fun CustomProgressIndicator() {
    var show by remember { mutableStateOf(true) }
    val timeout: Long = 10000
    LaunchedEffect(key1 = Unit){
        delay(timeout)
        show = false
    }
    if (show) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = Color.LightGray,
                strokeWidth = 5.dp
            )
        }
    }
}
@Composable
fun InfiniteScroll(
    itemCount: Int,
    loadMoreItems: () -> Unit,
    content: @Composable (Pair<Int, Boolean>) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(itemCount) { index ->
            var isLoading = index == itemCount - 1
            if (isLoading) {
                loadMoreItems()
            }
            content(Pair(index, isLoading))
        }
    }
}

@Composable
fun CharacterRow(character: Character) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name + "image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(all = 10.dp)) {
                Text(text = character.name, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                Text(text = character.species)
            }
        }
    }
}