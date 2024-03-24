package mx.edu.utttt.loginfire.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import mx.edu.utttt.loginfire.R.*

@SuppressLint("PrivateResource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldGeneric(
    value: String,
    onTextFieldChange: (String) -> Unit,
    painterResource: Painter,
    keyboardType: KeyboardType,
    placeholder: String,
    visualTransformation: VisualTransformation,
    action: ImeAction,
    errorMessage: String? = null,
){
    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = action
        ),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        visualTransformation = visualTransformation,
        isError = errorMessage!= null,
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
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
        )
    ) {
        // Icono del botón
        Icon(painter = painterResource, contentDescription = "")
        // Espaciador horizontal
        Spacer(modifier = Modifier.width(3.dp))
        // Texto del botón
        Text(text = name)
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onTextFieldChange: (String) -> Unit,
    painterResource: Painter,
    keyboardType: KeyboardType,
    placeholder: String,
    action: ImeAction,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder)},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = action
        ),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = errorMessage!= null,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = painterResource(id = drawable.eye), "")
            }
        }
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}