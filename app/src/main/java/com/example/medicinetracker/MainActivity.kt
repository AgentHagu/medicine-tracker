package com.example.medicinetracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.medicinetracker.data.local.MedicineDatabase
import com.example.medicinetracker.data.local.MedicineRepository
import com.example.medicinetracker.ui.medicine.MedicineListScreen
import com.example.medicinetracker.ui.medicine.MedicineListViewModel
import com.example.medicinetracker.ui.medicine.MedicineListViewModelFactory
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = MedicineDatabase.getDatabase(applicationContext)
        val repository = MedicineRepository(database.medicineDao())
        val viewModelFactory = MedicineListViewModelFactory(repository)
        val viewModel: MedicineListViewModel = viewModelFactory.create(MedicineListViewModel::class.java)

        setContent {
            MedicineTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MedicineListScreen(
                        viewModel = viewModel,
                        onAddMedicine = {
                            // TODO: Handle navigation to MedicineFormScreen
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}