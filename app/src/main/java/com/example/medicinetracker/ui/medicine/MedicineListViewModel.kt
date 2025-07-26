package com.example.medicinetracker.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinetracker.data.local.MedicineRepository
import com.example.medicinetracker.data.model.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MedicineListViewModel(private val repository: MedicineRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicineListUiState())
    val uiState: StateFlow<MedicineListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allMedicines.collect { medicines ->
                _uiState.update { it.copy(medicineList = medicines) }
            }
        }
    }

    fun addMedicine(medicine: Medicine) {
        viewModelScope.launch {
            repository.insert(medicine)
        }
    }

    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch {
            repository.delete(medicine)
        }
    }
}