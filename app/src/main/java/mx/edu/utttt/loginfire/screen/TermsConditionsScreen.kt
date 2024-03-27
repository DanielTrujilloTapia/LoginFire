package mx.edu.utttt.loginfire.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.utttt.loginfire.R


@Composable
fun TermsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        TermsAndConditions(onAccept = {
            navController.popBackStack()
        }, imageResId = R.drawable.donpe)
    }
}

@Composable
fun TermsAndConditions(onAccept: () -> Unit, imageResId: Int) {
    Surface(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Términos y Condiciones",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 5.dp, top = 30.dp)
                )
            }

            Text(
                text = "Al aceptar estos términos y condiciones, usted consiente en proporcionar su información personal para los propósitos detallados en nuestra política de privacidad. Esta información puede incluir su nombre completo, dirección, número de teléfono, correo electrónico, fecha de nacimiento e identificación oficial (como el número de la INE). También podemos recopilar información adicional, como detalles financieros, historial de transacciones y navegación en línea, entre otros. Al proporcionar esta información, usted nos autoriza a utilizarla para diversos fines, como la creación y gestión de su cuenta, la prestación de servicios personalizados y la realización de análisis. Nos comprometemos a proteger su privacidad y a tratar su información de manera confidencial. No compartiremos su información con terceros no afiliados sin su consentimiento, excepto en casos legalmente justificados. Le recomendamos leer detenidamente estos términos y condiciones antes de aceptarlos. Si tiene alguna pregunta sobre cómo manejamos su información personal, no dude en comunicarse con nosotros. Estamos aquí para garantizar que sus datos estén protegidos y utilizados de manera responsable.",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.weight(6f).padding(horizontal = 30.dp, vertical = 30.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(2f) // La imagen ocupa 4/9 del espacio vertical disponible
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Firma sherk att: Daniel",
                    modifier = Modifier.size(320.dp).padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Spacer para distribuir espacio vertical

            Button(
                onClick = onAccept,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Regresar")
            }
        }
    }
}

