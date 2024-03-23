package mx.edu.utttt.loginfire.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.utttt.loginfire.screen.LoginScreen
import mx.edu.utttt.loginfire.screen.LoginViewModel
import mx.edu.utttt.loginfire.screen.MainScreen
import mx.edu.utttt.loginfire.screen.RegisterScreen
import mx.edu.utttt.loginfire.screen.RegisterViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(LoginViewModel(), navController) }
            composable("signup") { RegisterScreen(viewModel = RegisterViewModel(), navController) }
            composable("main") { MainScreen() }
        }
    }
}