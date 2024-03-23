package mx.edu.utttt.loginfire.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.utttt.loginfire.model.UserRepository

@Composable
fun MainScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
    {
        Text(text = "Bienvenido a la app :)")
    }
}