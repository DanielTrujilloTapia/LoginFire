package mx.edu.utttt.loginfire.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utttt.loginfire.components.GenericOutlinedButton
import mx.edu.utttt.loginfire.R.*
import mx.edu.utttt.loginfire.components.GenericButton
import mx.edu.utttt.loginfire.components.PasswordTextField
import mx.edu.utttt.loginfire.components.TextFieldGeneric
import mx.edu.utttt.loginfire.model.UserRepository

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        Login(Modifier.align(Alignment.Center), viewModel = viewModel, navController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    // Variables de error
    val emailError: String? by viewModel.emailError.observeAsState()
    val passwordError: String? by viewModel.passwordError.observeAsState()
    //Importante
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        TextFieldGeneric(
            value = email,
            onTextFieldChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
            painterResource = painterResource(id = drawable.email),
            keyboardType = KeyboardType.Email,
            placeholder = "Email",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = emailError
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = password,
            onTextFieldChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
            painterResource = painterResource(id = drawable.lock),
            keyboardType = KeyboardType.Password,
            placeholder = "Contraseña",
            action = ImeAction.Next,
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GenericOutlinedButton(
                name = "Registrarse",
                painterResource = painterResource(id = drawable.person_add),
                backColor = Color.DarkGray,
                color = Color.White
            ) {
                navController.navigate("signup")
            }
            GenericButton(
                name = "Iniciar Sesión",
                painterResource = painterResource(id = drawable.login),
                backColor = Color.DarkGray,
                color = Color.White,
            ) {
                try {
                    scope.launch {
                        viewModel.onEvent(LoginFormEvent.Submit)
                    }
                } catch (e: Exception) {
                    Log.e("UserLogin", "Error en el inicio de sesion", e)
                }
            }
        }
    }
}
