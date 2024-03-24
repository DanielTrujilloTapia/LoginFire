package mx.edu.utttt.loginfire.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.edu.utttt.loginfire.model.UserRepository
import mx.edu.utttt.loginfire.useCase.Validations
import java.time.LocalDate

class LoginViewModel(
    private val validations: Validations = Validations(),
    private val navController: NavController
) : ViewModel() {
    private val user = UserRepository()
    // Valores para para email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // Valores para password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Definiciones para errores
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                _email.value = event.email
            }
            is LoginFormEvent.PasswordChanged -> {
                _password.value = event.password
            }
            is LoginFormEvent.Submit -> {
                validateAllFields()
            }
        }
    }

    private fun validateAllFields() {
        val emailResult = validations.validateEmail(_email.value ?: "")
        val passwordResult = validations.validateStrongPassword(_password.value ?: "")

        val hasError = listOf(
            emailResult,
            passwordResult,
        ). any { !it.successful }

        if (hasError){
            _emailError.value = emailResult.errorMessage
            _passwordError.value = passwordResult.errorMessage
            return
        }
        viewModelScope.launch {
            try {
                user.login(
                    _email.value ?: "",
                    _password.value ?: "",
                    navController
                )
            } catch (e: Exception) {
                Log.e("UserLogin", "Error en el inicio de sesion", e)
            }
        }
    }
}