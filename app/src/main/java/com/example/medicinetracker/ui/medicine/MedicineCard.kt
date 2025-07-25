package com.example.medicinetracker.ui.medicine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinetracker.data.model.Medicine
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme
import java.time.LocalDate

// TODO: Hide usage until user taps MedicineCard for more info (supports multi-line usage desc)
@Composable
fun MedicineCard(
    medicine: Medicine,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Name: ${medicine.name}")
                Text("Quantity: ${medicine.quantity}")
                Text("Usage: ${medicine.usage}")
                Text("Expiry: ${medicine.expiryDate}")
            }

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MedicineCardPreview() {
    MedicineTrackerTheme {
        MedicineCard(
            medicine = Medicine(
                name = "Medicine Name",
                quantity = 10,
                usage = "Take with water",
                expiryDate = LocalDate.now()
            )
        )
    }
}
