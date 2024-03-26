package mx.edu.utttt.loginfire

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mx.edu.utttt.loginfire.navigation.AppNavigation
import mx.edu.utttt.loginfire.ui.theme.LoginFireTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            checkBiometricPreference(navController)
            LoginFireTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController)
                }
            }
        }
    }

    //Revisar las preferencias del usuario
    private fun checkBiometricPreference(navController: NavHostController) {
        val sharedPreferences = getSharedPreferences("MyAppPref", MODE_PRIVATE)
        val useBiometric = sharedPreferences.getBoolean("UseBiometric", false)

        if (useBiometric) {
            showBiometricPrompt(navController)
        }
    }

    //Mostrar mensaje para la huella
    private fun showBiometricPrompt(navController: NavHostController) {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this as FragmentActivity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                runOnUiThread {
                    navController.navigate("main")
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                navController.navigate("login")
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login")
            .setDescription("Usa tu huella para iniciar sesión")
            .setNegativeButtonText("Usar contraseña")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}