package com.example.learnwithme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learnwithme.data.datasource.character.remote.disney.RemoteDisneyCharactersDataSource
import com.example.learnwithme.data.datasource.character.remote.disney.api.DisneyApiInterFace
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.RemoteCharactersDataSource
import com.example.learnwithme.data.datasource.character.remote.rickandmorty.api.CharacterApiInterface
import com.example.learnwithme.data.repository.CharacterRepository
import com.example.learnwithme.domain.usecase.CharacterUseCase
import com.example.learnwithme.manager.NetworkManager
import com.example.learnwithme.presentation.detail.DetailCharactersView
import com.example.learnwithme.presentation.detail.DetailCharactersViewModel
import com.example.learnwithme.presentation.list.ListCharactersView
import com.example.learnwithme.presentation.list.ListCharactersViewModel
import com.example.learnwithme.ui.theme.LearnWithMeTheme
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rickandmortyDatasource = RemoteCharactersDataSource(
            characterApi = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/")
                .client(OkHttpClient())
                .build().create(CharacterApiInterface::class.java),
            network = NetworkManager()
        )

        val disneyDatasource = RemoteDisneyCharactersDataSource(
            disneyApi = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.disneyapi.dev/")
                .client(OkHttpClient())
                .build().create(DisneyApiInterFace::class.java),
            network = NetworkManager()
        )

        val vm = ListCharactersViewModel(
            useCase =  CharacterUseCase(
                repository = CharacterRepository(
                    dataSource = disneyDatasource
                )
            )
        )

        setContent {
            val navController = rememberNavController()
            LearnWithMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            ListCharactersView(viewModel = vm, navController = navController)
                        }
                        composable(
                            route="detail/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    /* configuring arguments for navigation */
                                    type = NavType.IntType
                                }
                            )
                        ) { navBackStackEntry->
                            val character = navBackStackEntry.arguments?.getInt("id")?.let { it }
                            if (character != null) {
                                val vm = DetailCharactersViewModel(
                                    id = character,
                                    useCase =  CharacterUseCase(
                                        repository = CharacterRepository(
                                            dataSource = disneyDatasource
                                        )
                                    )
                                )
                                DetailCharactersView(viewModel = vm)
                            }
                        }
                    }
                }
            }
        }
    }
}


