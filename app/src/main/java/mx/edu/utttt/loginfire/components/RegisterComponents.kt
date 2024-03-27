package mx.edu.utttt.loginfire.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment.Horizontal
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import mx.edu.utttt.loginfire.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import mx.edu.utttt.loginfire.R.*
import mx.edu.utttt.loginfire.screen.RegisterViewModel


@Composable
fun DatePickerField(
    birthdate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    drawable: Int,
    errorMessage: String? = null,
) {
    val context = LocalContext.current
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy/MM/dd") }
    val birthdateStr = remember { mutableStateOf(birthdate.format(formatter)) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = birthdateStr.value,
            shape = RoundedCornerShape(38),
            onValueChange = { },
            readOnly = true,
            placeholder = { Text("Fecha de nacimiento") },
            modifier = Modifier.fillMaxWidth(fraction = 0.80f),
            leadingIcon = {
                Icon(painter = painterResource(id = drawable), contentDescription = "Icono de calendario")
            },
            isError = errorMessage!= null,
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
                .background(Color.White)
        ) {
            Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "")
        }
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
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

@Composable
fun SexDropdown(
    viewModel: RegisterViewModel,
    trailingIcon: Int,
    leadingIcon: Int,
    errorMessage: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedOption by viewModel.sex.observeAsState("")

    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        OutlinedTextField(
            value = if (selectedOption.isEmpty()) "Selecciona el sexo" else selectedOption,
            shape = RoundedCornerShape(38),
            onValueChange = { },
            readOnly = true,
            placeholder = { Text("Sexo") },
            modifier = Modifier.fillMaxWidth(),leadingIcon = {
                Icon(painter = painterResource(id = leadingIcon), contentDescription = "Icono")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "Desplegar",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            isError = errorMessage!= null,
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(fraction = 0.92f)
        ) {
            DropdownMenuItem(
                text = { Text("H") },
                onClick = {
                    viewModel.updateSex("H")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("M") },
                onClick = {
                    viewModel.updateSex("M")
                    expanded = false
                }
            )
        }
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

@Composable
fun CheckBoxComponent(value: String, checked: Boolean, errorMessage: String? = null, onCheckedChange: (Boolean) -> Unit,onTextSelected: (String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                onCheckedChange(isChecked)
            }
        )
        ClickableTextComponent(value = value, onTextSelected = onTextSelected)
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit){
    val privacidadPoliticaText = "Para continuar debes aceptar Terminos y Politicas de privacidad"
    val terminosCondicionesText = " y Terminos de Uso"

    val anotacionString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue)){
            pushStringAnnotation(tag = privacidadPoliticaText, annotation = privacidadPoliticaText)
            append(privacidadPoliticaText)
        }
        withStyle(style = SpanStyle(color = Color.Blue)){
            pushStringAnnotation(tag = terminosCondicionesText, annotation = terminosCondicionesText)
            append(terminosCondicionesText)
        }
    }
    ClickableText(text = anotacionString, onClick = { offset ->
        anotacionString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "${span.item}")

                if ((span.item == terminosCondicionesText) || (span.item == privacidadPoliticaText)){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun TitleRegistro() {
    Text(
        text = "Registrar Usuario INE",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp, start = 80.dp, top = 30.dp)

    )
}