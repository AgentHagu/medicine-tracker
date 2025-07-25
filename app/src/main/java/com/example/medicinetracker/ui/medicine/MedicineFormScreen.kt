package com.example.medicinetracker.ui.medicine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinetracker.data.model.Medicine
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme
import java.time.LocalDate

// TODO: DatePicker for expiry date
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicineFormScreen(
    modifier: Modifier = Modifier,
    onSubmit: (Medicine) -> Unit = {} // TODO: Handle submission on Navigation side
) {
    var medicineName by remember { mutableStateOf("") }
    var medicineQuantity by remember { mutableStateOf("") }
    var medicineExpiryDate by remember { mutableStateOf("") }
    var medicineUsage by remember { mutableStateOf("") }

    Column {
        TextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Medicine Name") },
            modifier = modifier.fillMaxWidth()
        )

        TextField(
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

        TextField(
            value = medicineUsage,
            onValueChange = { medicineUsage = it },
            label = { Text("Usage") },
            modifier = modifier.fillMaxWidth()
        )

        // Visual preview for Users
        MedicineCard(
            medicine = Medicine(
                name = if (medicineName.isBlank()) "Empty Name" else medicineName,
                quantity = medicineQuantity.toIntOrNull() ?: 0,
                expiryDate = LocalDate.now(),
                usage = if (medicineUsage.isBlank()) "Empty Usage" else medicineUsage
            )
        )
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