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
import com.example.learnwithme.data.datasource.character.mock.MockCharacterDataSource
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
import com.example.learnwithme.presentation.navigation.AppNavHost
import com.example.learnwithme.presentation.navigation.Screen
import com.example.learnwithme.ui.theme.LearnWithMeTheme
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            LearnWithMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navController = navController,
                        startDestination = Screen.CharacterList.route,
                        context = applicationContext)
                }
            }
        }
    }
}


