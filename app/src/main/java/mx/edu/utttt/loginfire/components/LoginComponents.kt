package mx.edu.utttt.loginfire.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun GenericOutlinedButton(
    name: String,
    painterResource: Painter,
    backColor: Color,
    color: Color,
    onClick: () -> Unit
){
    // Botón con borde delgado y esquinas redondeadas
    OutlinedButton(
        shape = RoundedCornerShape(50),  // Forma redondeada para las esquinas
        onClick = onClick,  // Acción de clic del botón
        colors = ButtonDefaults.buttonColors(  // Colores personalizados para el botón
            contentColor = color,  // Color del texto del botón
            containerColor = backColor,  // Color de fondo del botón
        ),
    ) {
        // Icono del botón
        Icon(painter = painterResource, contentDescription = "")
        // Espaciador horizontal
        Spacer(modifier = Modifier.width(2.dp))
        // Texto del botón
        Text(text = name)
    }
}

@Composable
fun GenericButton(
    name: String,
    painterResource: Painter,
    backColor: Color,
    color: Color,
    loginEnable: Boolean,
    onClick: () -> Unit
){
    // Botón con borde delgado y esquinas redondeadas
    OutlinedButton(
        shape = RoundedCornerShape(50),  // Forma redondeada para las esquinas
        onClick = onClick,  // Acción de clic del botón
        colors = ButtonDefaults.buttonColors(  // Colores personalizados para el botón
            contentColor = color,  // Color del texto del botón
            containerColor = backColor,  // Color de fondo del botón
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        ),
        enabled = loginEnable
    ) {
        // Icono del botón
        Icon(painter = painterResource, contentDescription = "")
        // Espaciador horizontal
        Spacer(modifier = Modifier.width(2.dp))
        // Texto del botón
        Text(text = name)
    }
}