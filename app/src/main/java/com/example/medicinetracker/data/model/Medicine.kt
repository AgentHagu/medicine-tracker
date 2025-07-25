package com.example.medicinetracker.data.model

import java.time.LocalDate

data class Medicine(
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val expiryDate: LocalDate,
    val usage: String
)