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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mx.edu.utttt.loginfire.R
import mx.edu.utttt.loginfire.components.DatePickerField
import mx.edu.utttt.loginfire.components.GenericButton
import mx.edu.utttt.loginfire.components.PasswordTextField
import mx.edu.utttt.loginfire.components.TextFieldGeneric
import mx.edu.utttt.loginfire.model.UserRepository
import java.time.LocalDate

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        LazyColumn {
            item{
                Register(Modifier.align(Alignment.Center), viewModel, UserRepository())
            }
        }
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel, createUser: UserRepository) {
    // Variables ViewModel
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val lastname: String by viewModel.lastname.observeAsState(initial = "")
    val address: String by viewModel.address.observeAsState(initial = "")
    val municipality: String by viewModel.municipality.observeAsState(initial = "")
    val postalCode: String by viewModel.postalCode.observeAsState(initial = "")
    val sex: String by viewModel.sex.observeAsState(initial = "")
    val electorKey: String by viewModel.electorKey.observeAsState(initial = "")
    val curp: String by viewModel.curp.observeAsState(initial = "")
    val birthdate by viewModel.birthdate.observeAsState(LocalDate.now())
    val registerEnable:Boolean by viewModel.registerEnabled.observeAsState(initial = false)
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        TextFieldGeneric(
            value = email,
            onTextFieldChange = { viewModel.onRegisterChanged(it, password, confirmPassword, name, lastname, address, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.email),
            keyboardType = KeyboardType.Email,
            placeholder = "Email",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = password,
            onTextFieldChange = { viewModel.onRegisterChanged(email, it, confirmPassword, name, lastname, address, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.email),
            keyboardType = KeyboardType.Password,
            placeholder = "Contraseña",
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = confirmPassword,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, it, name, lastname, address, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.email),
            keyboardType = KeyboardType.Password,
            placeholder = "Confirmar Contraseña",
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = name,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, it, lastname, address, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.person),
            keyboardType = KeyboardType.Text,
            placeholder = "Nombre",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = lastname,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, it, address, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.person),
            keyboardType = KeyboardType.Text,
            placeholder = "Apellidos",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = address,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, it, municipality, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.location),
            keyboardType = KeyboardType.Text,
            placeholder = "Domicilio",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = municipality,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, address, it, postalCode, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.location),
            keyboardType = KeyboardType.Text,
            placeholder = "Municipio",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = postalCode,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, address, municipality, it, sex, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.signpost),
            keyboardType = KeyboardType.Number,
            placeholder = "Código Postal",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = sex,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, address, municipality, postalCode, it, electorKey, curp) },
            painterResource = painterResource(id = R.drawable.person_sex),
            keyboardType = KeyboardType.Text,
            placeholder = "Sexo",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = electorKey,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, address, municipality, postalCode, sex, it, curp) },
            painterResource = painterResource(id = R.drawable.key),
            keyboardType = KeyboardType.Text,
            placeholder = "Clave de elector",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = curp,
            onTextFieldChange = { viewModel.onRegisterChanged(email, password, confirmPassword, name, lastname, address, municipality, postalCode, sex, electorKey, it) },
            painterResource = painterResource(id = R.drawable.key),
            keyboardType = KeyboardType.Text,
            placeholder = "CURP",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Done
        )
        Spacer(modifier = Modifier.height(10.dp))
        DatePickerField(
            birthdate = viewModel.birthdate.value ?: LocalDate.now(),
            onDateSelected = { newDate -> viewModel.updateBirthdate(newDate) },
            drawable = R.drawable.cake
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GenericButton(
                name = "Registrate!",
                painterResource = painterResource(id = R.drawable.person_add),
                backColor = Color.DarkGray,
                color = Color.White,
                buttonEnable = registerEnable
            ) {
                scope.launch {
                    try {
                        createUser.createUser(email, password, name, lastname, address, municipality, postalCode, sex, electorKey, curp, birthdate)
                    } catch (e: Exception) {
                        Log.e("UserCreation", "Error creating user", e)
                    }
                }
            }
        }
    }
}