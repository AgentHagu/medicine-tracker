package com.example.medicinetracker.ui.medicine

import com.example.medicinetracker.data.model.Medicine

data class MedicineListUiState(
    val medicines: List<Medicine> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)