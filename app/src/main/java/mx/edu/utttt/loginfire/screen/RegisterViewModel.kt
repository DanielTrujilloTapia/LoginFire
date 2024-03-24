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
import mx.edu.utttt.loginfire.useCase.ValidationResult
import mx.edu.utttt.loginfire.useCase.Validations
import java.time.LocalDate

class RegisterViewModel(
    private val validations: Validations = Validations(),
    private val navController: NavController
): ViewModel() {
    private val user = UserRepository()
    // Valores para para email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // Valores para password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Valores para password
    private val _repeatedPassword = MutableLiveData<String>()
    val repeatedPassword: LiveData<String> = _repeatedPassword

    // Valores para Nombre
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    // Valores para Apellidos
    private val _lastname = MutableLiveData<String>()
    val lastname: LiveData<String> = _lastname

    // Valores para Dirección
    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    // Valores para Municipio
    private val _municipality = MutableLiveData<String>()
    val municipality: LiveData<String> = _municipality

    // Valores para Código Postal
    private val _postalCode = MutableLiveData<String>()
    val postalCode: LiveData<String> = _postalCode

    // Valores para Código Sexo
    private val _sex = MutableLiveData<String>()
    val sex: LiveData<String> = _sex

    // Valores para Código Clave de elector
    private val _electorKey = MutableLiveData<String>()
    val electorKey: LiveData<String> = _electorKey

    // Valores para CURP
    private val _curp = MutableLiveData<String>()
    val curp: LiveData<String> = _curp

    // Valores para Terminos
    private val _acceptedTerms = MutableLiveData<Boolean>()
    val acceptedTerms: LiveData<Boolean> = _acceptedTerms

    // Valores para Fecha de nacimiento
    private val _birthdate = MutableLiveData<LocalDate>()
    val birthdate: LiveData<LocalDate> = _birthdate

    // Definiciones para errores
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _repeatedPasswordError = MutableLiveData<String?>()
    val repeatedPasswordError: LiveData<String?> = _repeatedPasswordError

    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?> = _nameError

    private val _lastnameError = MutableLiveData<String?>()
    val lastnameError: LiveData<String?> = _lastnameError

    private val _addressError = MutableLiveData<String?>()
    val addressError: LiveData<String?> = _addressError

    private val _municipalityError = MutableLiveData<String?>()
    val municipalityError: LiveData<String?> = _municipalityError

    private val _postalCodeError = MutableLiveData<String?>()
    val postalCodeError: LiveData<String?> = _postalCodeError

    private val _sexError = MutableLiveData<String?>()
    val sexError: LiveData<String?> = _sexError

    private val _electorKeyError = MutableLiveData<String?>()
    val electorKeyError: LiveData<String?> = _electorKeyError

    private val _curpError = MutableLiveData<String?>()
    val curpError: LiveData<String?> = _curpError

    private val _acceptedTermsError = MutableLiveData<String?>()
    val acceptedTermsError: LiveData<String?> = _acceptedTermsError

    private val _birthdateError = MutableLiveData<String?>()
    val birthdateError: LiveData<String?> = _birthdateError

    // Función para validar todos los campos
    fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.EmailChanged -> {
                _email.value = event.email
            }
            is RegisterFormEvent.PasswordChanged -> {
                _password.value = event.password
            }
            is RegisterFormEvent.RepeatedPasswordChanged -> {
                _repeatedPassword.value = event.repeatedPassword
            }
            is RegisterFormEvent.NameChanged -> {
                _name.value = event.name
            }
            is RegisterFormEvent.LastnameChanged -> {
                _lastname.value = event.lastname
            }
            is RegisterFormEvent.AddressChanged -> {
                _address.value = event.address
            }
            is RegisterFormEvent.MunicipalityChanged -> {
                _municipality.value = event.municipality
            }
            is RegisterFormEvent.PostalCodeChanged -> {
                _postalCode.value = event.postalCode
            }
            is RegisterFormEvent.SexChanged -> {
                _sex.value = event.sex
            }
            is RegisterFormEvent.ElectorKeyChanged -> {
                _electorKey.value = event.electorKey
            }
            is RegisterFormEvent.CurpChanged -> {
                _curp.value = event.curp
            }
            is RegisterFormEvent.AcceptTerms -> {
                _acceptedTerms.value = event.isAccepted
            }
            is RegisterFormEvent.BirthdateChanged -> {
                _birthdate.value = event.birthdate
            }
            is RegisterFormEvent.Submit -> {
                validateAllFields()
            }
        }
    }

    private fun validateAllFields() {
        val emailResult = validations.validateEmail(_email.value ?: "")
        val passwordResult = validations.validateStrongPassword(_password.value ?: "")
        val repeatedPasswordResult = validations.validateRepeatedPassword(_password.value ?: "", _repeatedPassword.value ?: "")
        val nameResult = validations.validateBasicText(_name.value ?: "")
        val lastnameResult = validations.validateBasicText(_lastname.value ?: "")
        val addressResult = validations.validateBasicText(_address.value ?: "")
        val municipalityResult = validations.validateBasicText(_municipality.value ?: "")
        val postalCodeResult = validations.validatePostalCode(_postalCode.value ?: "")
        val sexResult = validations.validateSex(_sex.value ?: "")
        val electorKeyResult = validations.validateElectorKey(_electorKey.value ?: "")
        val curpResult = validations.validateCurp(_curp.value ?: "")
        val birthdateResult = validations.validateBirthdate(_birthdate.value ?: LocalDate.now())
        val acceptTermsResult = validations.validateTerms(_acceptedTerms.value ?: false)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            nameResult,
            lastnameResult,
            addressResult,
            municipalityResult,
            postalCodeResult,
            sexResult,
            electorKeyResult,
            curpResult,
            birthdateResult,
            acceptTermsResult
        ). any { !it.successful }

        if (hasError){
            _emailError.value = emailResult.errorMessage
            _passwordError.value = passwordResult.errorMessage
            _repeatedPasswordError.value = repeatedPasswordResult.errorMessage
            _nameError.value = nameResult.errorMessage
            _lastnameError.value = lastnameResult.errorMessage
            _addressError.value = addressResult.errorMessage
            _municipalityError.value = municipalityResult.errorMessage
            _postalCodeError.value = postalCodeResult.errorMessage
            _sexError.value = sexResult.errorMessage
            _electorKeyError.value = electorKeyResult.errorMessage
            _curpError.value = curpResult.errorMessage
            _birthdateError.value = birthdateResult.errorMessage
            _acceptedTermsError.value = acceptTermsResult.errorMessage
            return
        }
        viewModelScope.launch {
            try {
                val strBrithdate = _birthdate.value.toString()
                user.createUser(
                    _email.value ?: "",
                    _password.value ?: "",
                    _name.value ?: "",
                    _lastname.value ?: "",
                    _address.value ?: "",
                    _municipality.value ?: "",
                    _postalCode.value ?: "",
                    _sex.value ?: "",
                    _electorKey.value ?: "",
                    _curp.value ?: "",
                    strBrithdate ?: "",
                    navController
                )
            } catch (e: Exception) {
                Log.e("UserLogin", "Error en el inicio de sesion", e)
            }
        }
    }

    // Función para actualizar la fecha de nacimiento
    fun updateBirthdate(newDate: LocalDate) {
        _birthdate.value = newDate
    }

    //Función para actualizar el sexo
    fun updateSex(newSex: String) {
        _sex.value = newSex
    }

    fun acceptTerms(accept: Boolean) {
        _acceptedTerms.value = accept
    }
}