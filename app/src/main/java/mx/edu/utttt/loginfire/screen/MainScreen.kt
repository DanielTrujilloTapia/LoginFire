package mx.edu.utttt.loginfire.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import mx.edu.utttt.loginfire.R.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mx.edu.utttt.loginfire.components.GenericButton
import mx.edu.utttt.loginfire.components.GenericOutlinedButton
import mx.edu.utttt.loginfire.model.UserRepository

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Welcome(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Welcome(
    modifier: Modifier,
    viewModel: MainViewModel
){
    val user = UserRepository()
    val name: String by viewModel.name.observeAsState(initial = "...")
    val lastname: String by viewModel.lastname.observeAsState(initial = "...")
    val address: String by viewModel.address.observeAsState(initial = "...")
    val municipality: String by viewModel.municipality.observeAsState(initial = "...")
    val postalCode: String by viewModel.postalCode.observeAsState(initial = "...")
    val sex: String by viewModel.sex.observeAsState(initial = "...")
    val electorKey: String by viewModel.electorKey.observeAsState(initial = "...")
    val curp: String by viewModel.curp.observeAsState(initial = "...")
    val birthdate: String by viewModel.birthdate.observeAsState(initial = "...")
    //Importante
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.wrapContentSize(),
        ) {
            Text(
                text = "Bienvenido a la app $name $lastname :)",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            UserInfoRow("Dirección", address)
            UserInfoRow("Municipio", municipality)
            UserInfoRow("Código Postal", postalCode)
            UserInfoRow("Sexo", sex)
            UserInfoRow("Clave de elector", electorKey)
            UserInfoRow("CURP", curp)
            UserInfoRow("Fecha de nacimiento", birthdate)
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GenericButton(name = "Obtener mis datos", painterResource = painterResource(id = drawable.person_sex), Color.DarkGray, Color.White) {
                    scope.launch {
                        viewModel.obtainUserInfo(user.userId, context)
                    }
                }
            }
        }
    }
}

@Composable
fun UserInfoRow(label: String, info: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = info,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}