package com.example.assignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

@Dao
interface ClassDao {

    @Query("SELECT * FROM ClassEntity")
    fun getAllClass(): Flow<List<ClassEntity>>

    @Query("""
            SELECT * FROM ClassEntity
            WHERE classId = :classId
            """)
    fun getClassById(classId:Int): ClassEntity?

    @Query("DELETE FROM ClassEntity")
    fun delTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(classEntity: ClassEntity)

    @Delete
    suspend fun deleteClass(classEntity: ClassEntity)

    @Update
    suspend fun updateClass(classEntity: ClassEntity)

    @Query("SELECT COUNT(dayOfWeek) FROM ClassEntity")
    fun getCountByDayOfWeek(day: Int): Int?

    @Query("SELECT COUNT(*) FROM ClassEntity WHERE dayOfWeek = :dayOfWeek AND time = :time")
    fun getCountByDayOfWeekAndTime(dayOfWeek: Int, time: String): Int?

    @Query("SELECT classroom FROM ClassEntity WHERE dayOfWeek = :dayOfWeek AND time = :time")
    fun getClassByDayOfWeekAndTimeAndClassroom(dayOfWeek: Int, time: String): List<String>
}