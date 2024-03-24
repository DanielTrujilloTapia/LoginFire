package mx.edu.utttt.loginfire.useCase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
