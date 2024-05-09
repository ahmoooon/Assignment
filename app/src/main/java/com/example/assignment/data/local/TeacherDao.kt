package com.example.assignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {

    @Query("SELECT * FROM TeacherEntity")
    fun getAllTeacher(): Flow<List<TeacherEntity>>

    @Query("""
            SELECT * FROM TeacherEntity
            WHERE id = :id
            """)
    fun getTeacherById(id:Int): TeacherEntity?

    @Query("SELECT COUNT(*) FROM TeacherEntity")
    fun getTeacherCount(): Int

    @Query("DELETE FROM TeacherEntity")
    fun delTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacherEntity: TeacherEntity)

    @Delete
    suspend fun deleteTeacher(teacherEntity: TeacherEntity)

    @Update
    suspend fun updateTeacher(teacherEntity: TeacherEntity)

    @Query("SELECT id FROM TeacherEntity WHERE firstname = :firstname AND lastname = :lastname")
    fun getTeacherIdByName(firstname: String, lastname: String): Int?
}