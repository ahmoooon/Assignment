package com.example.assignment.data.local

import kotlinx.coroutines.flow.Flow

class OfflineTeacherRepository(
    private val dao: TeacherDao
) : TeacherRepository {
    override fun getAllTeacher(): Flow<List<TeacherEntity>> = dao.getAllTeacher()
    override fun getTeacherById(id: Int): TeacherEntity? = dao.getTeacherById(id)
    override fun getTeacherCount(): Int = dao.getTeacherCount()
    override suspend fun insertTeacher(teacher: TeacherEntity) = dao.insertTeacher(teacher)
    override suspend fun deleteTeacher(teacher: TeacherEntity) = dao.deleteTeacher(teacher)
    override suspend fun updateTeacher(teacher: TeacherEntity) = dao.updateTeacher(teacher)
}