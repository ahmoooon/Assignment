package com.example.assignment.data.local

import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    fun getAllTeacher(): Flow<List<TeacherEntity>>

    fun getTeacherById(id: Int): TeacherEntity?

    fun getTeacherCount():Int

    suspend fun insertTeacher(teacher: TeacherEntity)

    suspend fun deleteTeacher(teacher: TeacherEntity)

    suspend fun updateTeacher(teacher: TeacherEntity)
}