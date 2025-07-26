package com.example.medicinetracker.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medicinetracker.data.local.MedicineRepository

class MedicineListViewModelFactory(private val repository: MedicineRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MedicineListViewModel(repository) as T
    }
}