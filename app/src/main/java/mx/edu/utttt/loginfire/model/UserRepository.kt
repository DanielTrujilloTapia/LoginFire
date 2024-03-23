package mx.edu.utttt.loginfire.model

import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utttt.loginfire.screen.LoginViewModel
import mx.edu.utttt.loginfire.screen.RegisterViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

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
        birthdate: LocalDate,
        navController: NavController
    ) {
        try {
            // Crear usuario con FirebaseAuth
            val userResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = userResult.user?.uid ?: throw Exception("User ID is null")
            val sendVerification = userResult.user!!
            sendVerification.sendEmailVerification().await()

            // Formatear la fecha de nacimiento a un String
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val birthdateStr = birthdate.format(formatter)

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
                "birthdate" to birthdateStr
            )

            // Guardar los datos del usuario en Firestore
            db.collection("users").document(userId).set(usuario).await()

            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        } catch (e: Exception) {
            throw Exception("Error creating user: ${e.message}")
        }
    }

    suspend fun login(
        email: String,
        password: String,
        navController: NavController
    ) {
        try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null && user.isEmailVerified) {
                navController.navigate("main") {
                    popUpTo("login") { inclusive = true }
                }
            } else {
                Log.d("Login", "El correo no esta verificado")
            }
        } catch (e: Exception) {
            throw Exception("Error creating user: ${e.message}")
        }
    }
}