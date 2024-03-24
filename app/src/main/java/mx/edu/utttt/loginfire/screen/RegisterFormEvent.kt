package mx.edu.utttt.loginfire.screen

import java.time.LocalDate

sealed class RegisterFormEvent {
    data class EmailChanged(val email: String): RegisterFormEvent()
    data class PasswordChanged(val password: String): RegisterFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegisterFormEvent()
    data class NameChanged(val name: String): RegisterFormEvent()
    data class LastnameChanged(val lastname: String): RegisterFormEvent()
    data class AddressChanged(val address: String): RegisterFormEvent()
    data class MunicipalityChanged(val municipality: String): RegisterFormEvent()
    data class PostalCodeChanged(val postalCode: String): RegisterFormEvent()
    data class SexChanged(val sex: String): RegisterFormEvent()
    data class ElectorKeyChanged(val electorKey: String): RegisterFormEvent()
    data class CurpChanged(val curp: String): RegisterFormEvent()
    data class AcceptTerms(val isAccepted: Boolean): RegisterFormEvent()
    data class BirthdateChanged(val birthdate: LocalDate): RegisterFormEvent()

    object Submit: RegisterFormEvent()
}