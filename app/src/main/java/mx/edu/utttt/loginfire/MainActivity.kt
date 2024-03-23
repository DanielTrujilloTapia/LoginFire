package mx.edu.utttt.loginfire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utttt.loginfire.navigation.AppNavigation
import mx.edu.utttt.loginfire.screen.LoginScreen
import mx.edu.utttt.loginfire.screen.LoginViewModel
import mx.edu.utttt.loginfire.screen.RegisterScreen
import mx.edu.utttt.loginfire.screen.RegisterViewModel
import mx.edu.utttt.loginfire.ui.theme.LoginFireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginFireTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}