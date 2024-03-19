package mx.edu.utttt.loginfire.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mx.edu.utttt.loginfire.components.GenericOutlinedButton
import mx.edu.utttt.loginfire.R.*
import mx.edu.utttt.loginfire.components.EmailField
import mx.edu.utttt.loginfire.components.GenericButton
import mx.edu.utttt.loginfire.components.PasswordField

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable:Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val isLoading:Boolean by viewModel.isLoading.observeAsState(initial = false)
    val scope = rememberCoroutineScope()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = modifier
        ) {
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GenericButton(
                    name = "Iniciar Sesión",
                    painterResource = painterResource(id = drawable.baseline_login),
                    backColor = Color.DarkGray,
                    color = Color.White,
                    loginEnable = loginEnable,
                ) {
                    scope.launch {
                        viewModel.onLoginSelected()
                    }
                }
                GenericOutlinedButton(
                    name = "Registrarse",
                    painterResource = painterResource(id = drawable.baseline_person_add),
                    backColor = Color.DarkGray,
                    color = Color.White
                ) {

                }
            }
        }
    }
}