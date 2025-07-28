package com.example.medicinetracker.ui.medicine

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinetracker.data.model.Medicine
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme
import java.time.LocalDate
import java.util.Calendar

// TODO: DatePicker for expiry date
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicineFormScreen(
    modifier: Modifier = Modifier,
    onSubmit: (Medicine) -> Unit = {}
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var medicineName by remember { mutableStateOf("") }
    var medicineQuantity by remember { mutableStateOf("") }
    var medicineExpiryDate by remember { mutableStateOf<LocalDate?>(null) }
    var medicineUsage by remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            medicineExpiryDate = LocalDate.of(year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val medicine = Medicine(
        name = if (medicineName.isBlank()) "Empty Name" else medicineName,
        quantity = medicineQuantity.toIntOrNull() ?: 0,
        expiryDate = medicineExpiryDate?.toEpochDay() ?: 0L,
        usage = if (medicineUsage.isBlank()) "Empty Usage" else medicineUsage
    )

    Column {
        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Medicine Name") },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = medicineQuantity,
            onValueChange = {
                if (it.isBlank() or (it.toIntOrNull() != null)) {
                    medicineQuantity = it.toString()
                }
            },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = medicineExpiryDate?.toString() ?: "",
            onValueChange = {},
            label = { Text("Expiry Date") },
            readOnly = true,
            modifier = modifier.fillMaxWidth()
                .clickable { datePickerDialog.show() },
        )

        OutlinedTextField(
            value = medicineUsage,
            onValueChange = { medicineUsage = it },
            label = { Text("Usage") },
            modifier = modifier.fillMaxWidth()
        )

        // Visual preview for Users
        Spacer(Modifier.height(16.dp))
        MedicineCard(
            medicine = medicine
        )

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { onSubmit(medicine) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MedicineFormScreenPreview() {
    MedicineTrackerTheme {
        MedicineFormScreen()
    }
}