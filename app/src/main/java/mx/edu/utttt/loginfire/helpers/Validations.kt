package mx.edu.utttt.loginfire.helpers

import android.util.Patterns
import java.time.LocalDate

object Validations {
    // Expresiones regulares para las validaciones
    private val STRONG_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
    private val BASIC_TEXT = "^[^\\s].{2,}$".toRegex()
    private val POSTAL_CODE = "^\\d{4,6}$".toRegex()
    private val SEX = "^[HM]$".toRegex()
    private val ELECTOR_KEY = "[A-Z]{6}[0-9]{8}[A-Z]{1}[0-9]{3}".toRegex() // Esta no necesita ajuste al final porque no usa ^ o $ para indicar inicio/fin de cadena
    private val CURP_REGEX = "^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$".toRegex()

    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isValidPassword(password: String): Boolean = password.length >= 8
    fun isStrongPassword(password: String): Boolean = STRONG_PASSWORD.matches(password)
    fun isBasicText(text: String): Boolean = BASIC_TEXT.matches(text)
    fun isPostalCode(postalCode: String): Boolean = POSTAL_CODE.matches(postalCode)
    fun isValidSex(sex: String): Boolean = SEX.matches(sex)
    fun isValidElectorKey(electorKey: String): Boolean = ELECTOR_KEY.matches(electorKey)
    fun isValidCurp(curp: String): Boolean = CURP_REGEX.matches(curp)

}
