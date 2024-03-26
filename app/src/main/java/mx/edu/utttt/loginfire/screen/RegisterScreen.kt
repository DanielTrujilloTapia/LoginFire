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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mx.edu.utttt.loginfire.R
import mx.edu.utttt.loginfire.components.CheckBoxComponent
import mx.edu.utttt.loginfire.components.DatePickerField
import mx.edu.utttt.loginfire.components.GenericButton
import mx.edu.utttt.loginfire.components.PasswordTextField
import mx.edu.utttt.loginfire.components.SexDropdown
import mx.edu.utttt.loginfire.components.TextFieldGeneric
import mx.edu.utttt.loginfire.model.UserRepository
import java.time.LocalDate

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        LazyColumn {
            item{
                Register(Modifier.align(Alignment.Center), viewModel, navController, UserRepository())
            }
        }
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel, navController: NavController, register: UserRepository) {
    // Variables ViewModel
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val repeatedPassword: String by viewModel.repeatedPassword.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val lastname: String by viewModel.lastname.observeAsState(initial = "")
    val address: String by viewModel.address.observeAsState(initial = "")
    val municipality: String by viewModel.municipality.observeAsState(initial = "")
    val postalCode: String by viewModel.postalCode.observeAsState(initial = "")
    val sex: String by viewModel.sex.observeAsState(initial = "")
    val electorKey: String by viewModel.electorKey.observeAsState(initial = "")
    val curp: String by viewModel.curp.observeAsState(initial = "")
    val birthdate by viewModel.birthdate.observeAsState(LocalDate.now())
    val acceptTerms: Boolean by viewModel.acceptedTerms.observeAsState(initial = false)
    // Variables de error
    val emailError: String? by viewModel.emailError.observeAsState()
    val passwordError: String? by viewModel.passwordError.observeAsState()
    val repeatedPasswordError: String? by viewModel.repeatedPasswordError.observeAsState()
    val nameError: String? by viewModel.nameError.observeAsState()
    val lastnameError: String? by viewModel.lastnameError.observeAsState()
    val addressError: String? by viewModel.addressError.observeAsState()
    val municipalityError: String? by viewModel.municipalityError.observeAsState()
    val postalCodeError: String? by viewModel.postalCodeError.observeAsState()
    val sexError: String? by viewModel.sexError.observeAsState()
    val electorKeyError: String? by viewModel.electorKeyError.observeAsState()
    val curpError: String? by viewModel.curpError.observeAsState()
    val acceptedTermsError: String? by viewModel.acceptedTermsError.observeAsState()
    val birthdateError: String? by viewModel.birthdateError.observeAsState()
    //Importante
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        //Email
        TextFieldGeneric(
            value = email,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.EmailChanged(it), context) },
            painterResource = painterResource(id = R.drawable.email),
            keyboardType = KeyboardType.Email,
            placeholder = "Email",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = emailError
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Password
        PasswordTextField(
            value = password,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.PasswordChanged(it), context) },
            painterResource = painterResource(id = R.drawable.lock),
            keyboardType = KeyboardType.Password,
            placeholder = "Contraseña",
            action = ImeAction.Next,
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(
            value = repeatedPassword,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.RepeatedPasswordChanged(it), context) },
            painterResource = painterResource(id = R.drawable.lock),
            keyboardType = KeyboardType.Password,
            placeholder = "Confirmar Contraseña",
            action = ImeAction.Next,
            errorMessage = repeatedPasswordError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = name,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.NameChanged(it), context) },
            painterResource = painterResource(id = R.drawable.person),
            keyboardType = KeyboardType.Text,
            placeholder = "Nombre",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = nameError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = lastname,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.LastnameChanged(it), context) },
            painterResource = painterResource(id = R.drawable.person),
            keyboardType = KeyboardType.Text,
            placeholder = "Apellidos",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = lastnameError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = address,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.AddressChanged(it), context) },
            painterResource = painterResource(id = R.drawable.location),
            keyboardType = KeyboardType.Text,
            placeholder = "Domicilio",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = addressError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = municipality,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.MunicipalityChanged(it), context) },
            painterResource = painterResource(id = R.drawable.location),
            keyboardType = KeyboardType.Text,
            placeholder = "Municipio",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = municipalityError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = postalCode,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.PostalCodeChanged(it), context) },
            painterResource = painterResource(id = R.drawable.signpost),
            keyboardType = KeyboardType.Number,
            placeholder = "Código Postal",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = postalCodeError
        )
        Spacer(modifier = Modifier.height(10.dp))
        SexDropdown(viewModel = viewModel, R.drawable.arrow_drop_down, R.drawable.person_sex, errorMessage = sexError)
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = electorKey,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.ElectorKeyChanged(it), context) },
            painterResource = painterResource(id = R.drawable.key),
            keyboardType = KeyboardType.Text,
            placeholder = "Clave de elector",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Next,
            errorMessage = electorKeyError
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldGeneric(
            value = curp,
            onTextFieldChange = { viewModel.onEvent(RegisterFormEvent.CurpChanged(it), context) },
            painterResource = painterResource(id = R.drawable.key),
            keyboardType = KeyboardType.Text,
            placeholder = "CURP",
            visualTransformation = VisualTransformation.None,
            action = ImeAction.Done,
            errorMessage = curpError
        )
        Spacer(modifier = Modifier.height(10.dp))
        DatePickerField(
            birthdate = viewModel.birthdate.value ?: LocalDate.now(),
            onDateSelected = { newDate -> viewModel.updateBirthdate(newDate) },
            drawable = R.drawable.cake,
            errorMessage = birthdateError
        )
        Spacer(modifier = Modifier.height(10.dp))
        CheckBoxComponent(
            value = stringResource(id = R.string.terminos),
            acceptTerms,
            errorMessage = acceptedTermsError,
            onCheckedChange = { viewModel.acceptTerms(it) },
            onTextSelected = {
            navController.navigate("terms") }
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
            ) {
                scope.launch {
                    try {
                        viewModel.onEvent(RegisterFormEvent.Submit, context)
                    } catch (e: Exception) {
                        Log.e("UserCreation", "Error creating user", e)
                    }
                }
            }
        }
    }
}