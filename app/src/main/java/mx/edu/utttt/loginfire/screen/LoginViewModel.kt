package mx.edu.utttt.loginfire.screen

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {
    // Valores para para email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // Valores para password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Valores para loginEnabled
    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    // Valores para loginEnabled
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length >= 8

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }
}