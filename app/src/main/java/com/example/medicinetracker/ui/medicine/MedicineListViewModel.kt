package com.example.medicinetracker.ui.medicine

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedicineListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MedicineListUiState())
    val uiState: StateFlow<MedicineListUiState> = _uiState.asStateFlow()
}