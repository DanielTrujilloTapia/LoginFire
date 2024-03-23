package mx.edu.utttt.loginfire.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.edu.utttt.loginfire.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import mx.edu.utttt.loginfire.R.*

@Composable
fun DatePickerField(
    birthdate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    drawable: Int
) {
    val context = LocalContext.current
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy/MM/dd") }
    val birthdateStr = remember { mutableStateOf(birthdate.format(formatter)) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = birthdateStr.value,
            onValueChange = { },
            readOnly = true,
            placeholder = { Text("Fecha de nacimiento") },
            modifier = Modifier.fillMaxWidth(fraction = 0.80f),
            leadingIcon = {
                Icon(painter = painterResource(id = drawable), contentDescription = "Icono de calendario")
            }
        )
        Spacer(modifier = Modifier.width(20.dp))
        IconButton(
            onClick = {
                showDatePicker(context, birthdate) { year, month, dayOfMonth ->
                    val newDate = LocalDate.of(year, month + 1, dayOfMonth)
                    birthdateStr.value = newDate.format(formatter)
                    onDateSelected(newDate)
                }
            },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
        }
    }
}

fun showDatePicker(
    context: Context,
    initialDate: LocalDate,
    onDateSelected: (year: Int, monthOfYear: Int, dayOfMonth: Int) -> Unit
) {
    val (year, month, day) = Triple(initialDate.year, initialDate.monthValue - 1, initialDate.dayOfMonth)
    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
        onDateSelected(selectedYear, selectedMonth, selectedDayOfMonth)
    }, year, month, day).show()
}