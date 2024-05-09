package com.example.assignment.data.local

import kotlinx.coroutines.flow.Flow

interface ClassRepository {
    fun getAllClass(): Flow<List<ClassEntity>>

    fun getClassById(id: Int): ClassEntity?

    suspend fun insertClass(classes: ClassEntity)

    suspend fun deleteClass(classes: ClassEntity)

    suspend fun updateClass(classes: ClassEntity)

    fun getCountByDayOfWeek(dayOfWeek: Int): Int?

    fun getCountByDayOfWeekAndTime(dayOfWeek: Int, time: String): Int?

    fun getClassByDayOfWeekAndTimeAndClassroom(dayOfWeek: Int, time: String): List<String>
}