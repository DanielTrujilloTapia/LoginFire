package mx.edu.utttt.loginfire.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import mx.edu.utttt.loginfire.helpers.Validations.isValidEmail
import mx.edu.utttt.loginfire.helpers.Validations.isValidPassword

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

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }
}