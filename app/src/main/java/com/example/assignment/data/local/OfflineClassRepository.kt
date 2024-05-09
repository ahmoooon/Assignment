package com.example.assignment.data.local

import kotlinx.coroutines.flow.Flow

class OfflineClassRepository(
    private val dao: ClassDao
) : ClassRepository {
    override fun getAllClass(): Flow<List<ClassEntity>> = dao.getAllClass()
    override fun getClassById(id: Int): ClassEntity? = dao.getClassById(id)
    override suspend fun insertClass(classes: ClassEntity) = dao.insertClass(classes)
    override suspend fun deleteClass(classes: ClassEntity) = dao.deleteClass(classes)
    override suspend fun updateClass(classes: ClassEntity) = dao.updateClass(classes)
    override fun getCountByDayOfWeek(dayOfWeek: Int): Int? = dao.getCountByDayOfWeek(dayOfWeek)
    override fun getCountByDayOfWeekAndTime(dayOfWeek: Int, time: String): Int? = dao.getCountByDayOfWeekAndTime(dayOfWeek, time)
    override fun getClassByDayOfWeekAndTimeAndClassroom(dayOfWeek: Int, time: String): List<String> = dao.getClassByDayOfWeekAndTimeAndClassroom(dayOfWeek,time)
}