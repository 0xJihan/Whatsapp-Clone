package com.jihan.whatsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jihan.whatsapp.presentation.destinations.Destination
import com.jihan.whatsapp.presentation.screens.HomeScreen
import com.jihan.whatsapp.presentation.screens.LoginScreen
import com.jihan.whatsapp.presentation.screens.SignupScreen
import com.jihan.whatsapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            AppTheme {
            MainApp()
            }

        }


        var backPressedTime = 0L
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime < 3000) {
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext, "Press back again to exit", Toast.LENGTH_SHORT
                    ).show()
                    backPressedTime = currentTime
                }
            }
        })


    }


    @Composable
    private fun MainApp() {

        val navController = rememberNavController()

        NavHost(navController, Destination.Login) {

            composable<Destination.Login>(exitTransition = {
                slideOutHorizontally { -it }
            }, popEnterTransition = { slideInHorizontally { -it } }) {
                LoginScreen(goToSignupScreen = { navController.navigate(Destination.Signup) }) {
                    navController.navigate(Destination.Home){
                        popUpTo<Destination.Login>{
                            inclusive=true
                        }
                    }
                }
            }

            composable<Destination.Signup>(enterTransition = { slideInHorizontally { it } },
                popExitTransition = { slideOutHorizontally { it } }) {
                SignupScreen(navigateUp = {navController.navigateUp()})
            }


            composable<Destination.Home> {
                HomeScreen()
            }



        }


    }
}


