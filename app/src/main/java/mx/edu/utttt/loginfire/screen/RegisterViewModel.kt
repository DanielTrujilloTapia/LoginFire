package mx.edu.utttt.loginfire.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.edu.utttt.loginfire.helpers.Validations.isBasicText
import mx.edu.utttt.loginfire.helpers.Validations.isPostalCode
import mx.edu.utttt.loginfire.helpers.Validations.isStrongPassword
import mx.edu.utttt.loginfire.helpers.Validations.isValidCurp
import mx.edu.utttt.loginfire.helpers.Validations.isValidElectorKey
import mx.edu.utttt.loginfire.helpers.Validations.isValidEmail
import mx.edu.utttt.loginfire.helpers.Validations.isValidSex
import java.time.LocalDate

class RegisterViewModel {
    // Valores para para email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // Valores para password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Valores para password
    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

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

    // Valores para Fecha de nacimiento
    private val _birthdate = MutableLiveData<LocalDate>()
    val birthdate: LiveData<LocalDate> = _birthdate

    // Valores para loginEnabled
    private val _registerEnabled = MutableLiveData<Boolean>()
    val registerEnabled: LiveData<Boolean> = _registerEnabled

    // Función para validar todos los campos
    fun onRegisterChanged(email: String, password: String, confirmPassword: String, name: String, lastname: String, address: String, municipality: String, postalCode: String, sex: String, electorKey: String, curp: String) {
        // Actualizar los valores observados
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _name.value = name
        _lastname.value = lastname
        _address.value = address
        _municipality.value = municipality
        _postalCode.value = postalCode
        _sex.value = sex
        _electorKey.value = electorKey
        _curp.value = curp

        // Evaluar todas las validaciones
        _registerEnabled.value =
                isValidEmail(email) &&
                isStrongPassword(password) &&
                isBasicText(name) &&
                isBasicText(lastname) &&
                isBasicText(address) &&
                isBasicText(municipality) &&
                isPostalCode(postalCode) &&
                isValidSex(sex) &&
                isValidElectorKey(electorKey) &&
                isValidCurp(curp) && (password == confirmPassword)
    }

    // Función para actualizar la fecha de nacimiento
    fun updateBirthdate(newDate: LocalDate) {
        _birthdate.value = newDate
    }
}