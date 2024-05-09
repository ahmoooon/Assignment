package com.example.assignment.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
    (
    foreignKeys = [
        ForeignKey(
            entity = TeacherEntity::class,
            parentColumns = ["id"],
            childColumns = ["teacherId"],
            onDelete = ForeignKey.CASCADE // Define onDelete behavior if needed
        )
    ]
)
data class ClassEntity(
    @PrimaryKey(autoGenerate = true)
    val classId: Int,
    val dayOfWeek: Int,
    val time: String,
    val classroom: String,
    val grade: Int,
    val teacherId: Int
)