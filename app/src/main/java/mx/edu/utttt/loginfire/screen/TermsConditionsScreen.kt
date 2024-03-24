package mx.edu.utttt.loginfire.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun TermsScreen(navController: NavController) {
    TermsAndConditions(onAccept = {
        navController.popBackStack()
    })
}

@Composable
fun TermsAndConditions(onAccept: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Términos y Condiciones",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Al aceptar estos términos y condiciones, usted está de acuerdo con brindar su información personal para los fines descritos en nuestra política de privacidad. Asegúrese de leer y entender completamente estos términos antes de aceptar.",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onAccept,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Regresar")
            }
        }
    }
}
