package com.example.medicinetracker.data.local

import com.example.medicinetracker.data.model.Medicine
import kotlinx.coroutines.flow.Flow

class MedicineRepository(private val medicineDao: MedicineDao) {
    val allMedicines: Flow<List<Medicine>> = medicineDao.getAll()

    suspend fun insert(medicine: Medicine) {
        medicineDao.insertMedicine(medicine)
    }

    suspend fun delete(medicine: Medicine) {
        medicineDao.deleteMedicine(medicine)
    }
}