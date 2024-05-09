package com.example.assignment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeacherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstname: String,
    val lastname: String,
    val password: String,
    val gender: String,
    val phone: String,
    val email: String,
    val subject: String
)

