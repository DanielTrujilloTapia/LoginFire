package mx.edu.utttt.loginfire.screen

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
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
        .padding(16.dp)){
        Login(Modifier.align(Alignment.Center), viewModel = viewModel, UserRepository(), navController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, login: UserRepository, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable:Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        TextFieldGeneric(
            value = email,
            onTextFieldChange = { viewModel.onLoginChanged(it, password) },
            painterResource = painterResource(id = drawable.email),
            keyboardType = KeyboardType.Email,
            placeholder = "Email",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = password,
            onTextFieldChange = { viewModel.onLoginChanged(email, it) },
            painterResource = painterResource(id = drawable.lock),
            keyboardType = KeyboardType.Password,
            placeholder = "Contraseña",
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GenericButton(
                name = "Iniciar Sesión",
                painterResource = painterResource(id = drawable.login),
                backColor = Color.DarkGray,
                color = Color.White,
                buttonEnable = loginEnable
            ) {
                try {
                    scope.launch {
                        login.login(email, password)
                    }
                } catch (e: Exception) {
                    Log.e("UserLogin", "Error en el inicio de sesion", e)
                }
            }
            GenericOutlinedButton(
                name = "Registrarse",
                painterResource = painterResource(id = drawable.person_add),
                backColor = Color.DarkGray,
                color = Color.White
            ) {
                navController.navigate("signup")
            }
        }
    }
}