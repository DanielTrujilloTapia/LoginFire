package mx.edu.utttt.loginfire.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import mx.edu.utttt.loginfire.screen.LoginViewModel
import mx.edu.utttt.loginfire.screen.RegisterViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    //Obtener uid del usuario
    private val user = Firebase.auth.currentUser
    val userId: String? = user?.uid

    //Función para el registro
    suspend fun createUser(
        email: String,
        password: String,
        name: String,
        lastname: String,
        address: String,
        municipality: String,
        postalCode: String,
        sex: String,
        electorKey: String,
        curp: String,
        birthdate: String,
        navController: NavController,
        context: Context
    ) {
        try {
            // Crear usuario con FirebaseAuth
            val userResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = userResult.user?.uid ?: throw Exception("User ID is null")
            val sendVerification = userResult.user!!
            sendVerification.sendEmailVerification().await()

            // Preparar los datos del usuario para guardar en Firestore
            val usuario = hashMapOf(
                "email" to email,
                "name" to name,
                "lastname" to lastname,
                "address" to address,
                "municipality" to municipality,
                "postalCode" to postalCode,
                "sex" to sex,
                "electorKey" to electorKey,
                "curp" to curp,
                "birthdate" to birthdate
            )

            // Guardar los datos del usuario en Firestore
            db.collection("users").document(userId).set(usuario).await()

            Toast.makeText(context, "El usuario se creo correctamente, revisa tu correo para activarlo", Toast.LENGTH_LONG).show()

            navController.navigate("login") {
                popUpTo("signup") { inclusive = true }
            }
        } catch (e: Exception) {
            throw Exception("Error creating user: ${e.message}")
        }
    }

    // Función para el Login
    suspend fun login(
        email: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null && user.isEmailVerified) {
                withContext(Dispatchers.Main) {
                    showDialogForBiometric(navController, context)
                }
            } else {
                Toast.makeText(context, "Tu correo no se encuentra verificado", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Ocurrio un error intenta mas tarde", Toast.LENGTH_SHORT).show()
            throw Exception("Error login user: ${e.message}")
        }
    }

    // Función para mostrar el dialogo de pregunta
    private fun showDialogForBiometric(navController: NavController, context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyAppPref", Context.MODE_PRIVATE)

        if (sharedPreferences.contains("UseBiometric")) {
            val useBiometric = sharedPreferences.getBoolean("UseBiometric", false)
            if (useBiometric) {
                navigateToMainScreen(navController)
            } else {
                navigateToMainScreen(navController)
                return
            }
        }

        val biometricManager = BiometricManager.from(context)
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Usar Huella Digital")
            .setDescription("¿Quieres usar la huella digital para inicios de sesión futuros?")
            .setNegativeButtonText("Cancelar")
            .build()

        val biometricPrompt = BiometricPrompt(context as FragmentActivity, ContextCompat.getMainExecutor(context), object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                saveBiometricPref(context, true)
                navigateToMainScreen(navController)
            }
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON || errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
                    saveBiometricPref(context, false)
                }

                navigateToMainScreen(navController)
            }
        })

        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            Log.d("Huella", "No se puede usar la huella en este dispositivo")
            navigateToMainScreen(navController)
        }
    }


    fun saveBiometricPref(context: Context, useBiometric: Boolean) {
        val sharedPref = context.getSharedPreferences("MyAppPref", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putBoolean("UseBiometric", useBiometric)
            apply()
        }
    }

    fun navigateToMainScreen(navController: NavController) {
        navController.navigate("main") {
            popUpTo("login") { inclusive = true }
        }
    }
}