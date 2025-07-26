package com.example.medicinetracker.ui.medicine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinetracker.data.model.Medicine
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import androidx.compose.runtime.getValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicineListContent(
    medicineList: List<Medicine>,
    onAddMedicine: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(medicineList) { medicine ->
                MedicineCard(medicine = medicine)
            }
        }

        FloatingActionButton(
            onClick = onAddMedicine,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Medicine")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicineListScreen(
    viewModel: MedicineListViewModel,
    onAddMedicine: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    MedicineListContent(
        medicineList = uiState.medicineList,
        onAddMedicine = onAddMedicine,
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MedicineListPreview() {
    val defaultMedicine = Medicine(
        name = "Medicine Name",
        quantity = 100,
        expiryDate = LocalDate.now().toEpochDay(),
        usage = "Take with water"
    )

    val medicineList = remember {
        mutableStateListOf(
            Medicine(
                name = "Panadol",
                quantity = 5,
                expiryDate = LocalDate.now().toEpochDay(),
                usage = "Take with water"
            ),
            Medicine(
                name = "Strepsil",
                quantity = 15,
                expiryDate = LocalDate.now().toEpochDay(),
                usage = "Suck in mouth"
            ),
            Medicine(
                name = "Cough Syrup",
                quantity = 1,
                expiryDate = LocalDate.now().toEpochDay(),
                usage = "Take with water"
            )
        )
    }

    MedicineTrackerTheme {
        MedicineListContent(
            medicineList = medicineList,
            onAddMedicine = {
                medicineList.add(defaultMedicine)
            }
        )
    }
}