@file:kotlin.OptIn(ExperimentalMaterial3Api::class)

package com.example.tugasinput


import android.media.Image
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentDataType.Companion.Date
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss
            }) { Text("Pilih") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {Text("Batal") }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun BioData(modifier: Modifier) {
    var textNamaDepan by remember { mutableStateOf("") }
    var textNamaBelakang by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textTglLahir by remember { mutableStateOf("") }
    var textExperience by remember { mutableStateOf("") }

    var checkAndroid by remember { mutableStateOf(false) }
    var checkUiUx by remember { mutableStateOf(false) }
    var checkWeb by remember { mutableStateOf(false) }

    var expandedDropdown by remember { mutableStateOf(false) }
    val experienceOption = listOf("<1Tahun", "1-3Tahun", ">3Tahun")

    var showDatePicker by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val isFormValid = textNamaDepan.isNotEmpty() &&
            textNamaBelakang.isNotEmpty() &&
            textEmail.isNotEmpty() &&
            textTglLahir.isNotEmpty() &&
            textExperience.isNotEmpty()

    val strAndroid = stringResource(R.string.android)
    val strUiUx = stringResource(R.string.ui_ux)
    val srtWeb = stringResource(R.string.web)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .align(Alignment.TopCenter)
        )
        Surface(
            modifier = Modifier
                .padding(top = 180.dp, bottom = 170.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(32.dp),
            shadowElevation = 24.dp,
            color = colorResource(R.color.white)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_xl))
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.title),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.purple_500)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_xs)))
                Text(
                    text = stringResource(R.string.subtitle),
                    fontSize = 13.sp,
                    color = colorResource(R.color.black)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                ) {
                    OutlinedTextField(
                        value = textNamaDepan, onValueChange = { textNamaDepan = it },
                        label = { Text(stringResource(R.string.nama_depan)) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    OutlinedTextField(
                        value = textNamaBelakang, onValueChange = { textNamaBelakang = it },
                        label = { Text(stringResource(R.string.nama_belakang)) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                OutlinedTextField(
                    value = textEmail, onValueChange = { textEmail = it },
                    label = { Text(stringResource(id = R.string.email)) },
                    modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = textTglLahir, onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(id = R.string.tgl_lahir)) },
                            trailingIcon = {
                                Icon(
                                    Icons.Default.DateRange,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
                        )
                        Box(
                            modifier = Modifier.matchParentSize()
                                .clickable { showDatePicker = true })
                    }
                    if (showDatePicker) {
                        DatePickerModal(
                            onDateSelected = { milidetik ->
                                if (milidetik != null) {
                                    val formatter =
                                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                    textTglLahir = formatter.format(Date(milidetik))
                                }
                            },
                            onDismiss = { showDatePicker = false }
                        )
                    }
                    ExposedDropdownMenuBox(
                        expanded = expandedDropdown,
                        onExpandedChange = { expandedDropdown = !expandedDropdown },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = textExperience, onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(id = R.string.experience)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDropdown) },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            modifier = Modifier.fillMaxWidth().menuAnchor(), shape = RoundedCornerShape(12.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expandedDropdown, onDismissRequest = { expandedDropdown = false }
                        ) {
                            experienceOption.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = { textExperience = option; expandedDropdown = false },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
