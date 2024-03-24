package mx.edu.utttt.loginfire.useCase

import android.util.Patterns
import java.time.LocalDate

class Validations {
    // Regex
    private val STRONG_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
    private val BASIC_TEXT = "^[^\\s].{2,}$".toRegex()
    private val POSTAL_CODE = "^\\d{4,6}$".toRegex()
    private val SEX = "^[HM]$".toRegex()
    private val ELECTOR_KEY = "[A-Z]{6}[0-9]{8}[A-Z]{1}[0-9]{3}".toRegex()
    private val CURP_REGEX = "^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$".toRegex()

    //Validación email
    fun validateEmail(email: String): ValidationResult{
        if (email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El email no debe de estar vacio"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "El email no es valido"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación contrseña segura
    fun validateStrongPassword(password: String): ValidationResult{
        if (password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "La contraseña no debe estar vacía."
            )
        }
        if (!STRONG_PASSWORD.matches(password)){
            return ValidationResult(
                successful = false,
                errorMessage = "La contraseña debe contener una letra mayúscula, una minúscula, un número, un caracter especial y tener mínimo 8 caracteres."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación texto basico
    fun validateBasicText(text: String): ValidationResult{
        if (text.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El campo no puede estar vacio."
            )
        }
        if (!BASIC_TEXT.matches(text)){
            return ValidationResult(
                successful = false,
                errorMessage = "No debe de haber espacios en blanco al principio."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación código postal
    fun validatePostalCode(postalCode: String): ValidationResult{
        if (postalCode.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El campo no puede estar vacio."
            )
        }
        if (!POSTAL_CODE.matches(postalCode)){
            return ValidationResult(
                successful = false,
                errorMessage = "Solo se permiten de 4 a 6 digitos."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación sexo
    fun validateSex(sex: String): ValidationResult{
        if (sex.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El campo no puede estar vacio."
            )
        }
        if (!SEX.matches(sex)){
            return ValidationResult(
                successful = false,
                errorMessage = "Solo se permite una sola letra H o M y sin espacios."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación clave de elector
    fun validateElectorKey(electorKey: String): ValidationResult{
        if (electorKey.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El campo no puede estar vacio."
            )
        }
        if (!ELECTOR_KEY.matches(electorKey)){
            return ValidationResult(
                successful = false,
                errorMessage = "No es una clave de elector valida."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación curp
    fun validateCurp(curp: String): ValidationResult{
        if (curp.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "El campo no puede estar vacio."
            )
        }
        if (!CURP_REGEX.matches(curp)){
            return ValidationResult(
                successful = false,
                errorMessage = "No es un CURP valido."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación password normal
    fun validatePassword(password: String): ValidationResult{
        if (password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "La contraseña debe contener por lo menos 8 caracteres"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación contraseña repetida
    fun validateRepeatedPassword(password: String, repeatedPassword: String): ValidationResult{
        if (password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Las contraseñas no coinciden"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    //Validación terminos y condiciones
    fun validateTerms(terms: Boolean): ValidationResult{
        if (!terms){
            return ValidationResult(
                successful = false,
                errorMessage = "Por favor acepta los terminos"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    // Validación cumpleaños
    fun validateBirthdate(birthdate: LocalDate): ValidationResult{
        if (birthdate == LocalDate.now()){
            return ValidationResult(
                successful = false,
                errorMessage = "Fecha invalida, la fecha introducida es hoy!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}