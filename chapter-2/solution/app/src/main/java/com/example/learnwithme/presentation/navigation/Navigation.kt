package com.example.learnwithme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.RemoteCharactersDataSource
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.repository.CharacterRepository
import com.example.learnwithme.domain.usecase.CharacterUseCase
import com.example.learnwithme.manager.NetworkManager
import com.example.learnwithme.presentation.detail.DetailCharactersView
import com.example.learnwithme.presentation.detail.DetailCharactersViewModel
import com.example.learnwithme.presentation.list.ListCharactersView
import com.example.learnwithme.presentation.list.ListCharactersViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    val rickDatasource = RemoteCharactersDataSource(
        characterApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/")
            .client(OkHttpClient())
            .build().create(CharacterApiInterface::class.java),
        network = NetworkManager()
    )
    val repository = CharacterRepository(dataSource = rickDatasource)
    val useCase = CharacterUseCase(repository = repository)
    val vm = ListCharactersViewModel(useCase = useCase)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.CharacterList.route) {
            ListCharactersView(viewModel = vm, navController = navController)
        }
        composable(
            route=Screen.CharacterDetail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry->
            val character = navBackStackEntry.arguments?.getInt("id")?.let { it }
            if (character != null) {
                val vm = DetailCharactersViewModel(
                    id = character,
                    useCase =  useCase
                )
                DetailCharactersView(viewModel = vm)
            }
        }
    }
}