package com.example.learnwithme.presentation.list.customview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

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