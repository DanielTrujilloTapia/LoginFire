package mx.edu.utttt.loginfire.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.edu.utttt.loginfire.screen.LoginScreen
import mx.edu.utttt.loginfire.screen.LoginViewModel
import mx.edu.utttt.loginfire.screen.MainScreen
import mx.edu.utttt.loginfire.screen.MainViewModel
import mx.edu.utttt.loginfire.screen.RegisterScreen
import mx.edu.utttt.loginfire.screen.RegisterViewModel
import mx.edu.utttt.loginfire.screen.TermsScreen
import mx.edu.utttt.loginfire.useCase.Validations

@Composable
fun AppNavigation(navController: NavHostController){
    val validations = Validations()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(LoginViewModel(validations, navController), navController) }
            composable("signup") { RegisterScreen(RegisterViewModel(validations, navController), navController) }
            composable("terms") { TermsScreen(navController) }
            composable("main") { MainScreen(MainViewModel()) }
        }
    }
}