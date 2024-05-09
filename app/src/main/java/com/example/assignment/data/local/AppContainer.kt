package com.example.assignment.data.local

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val teacherRepository: TeacherRepository
    val classRepository: ClassRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val teacherRepository: TeacherRepository by lazy {
        OfflineTeacherRepository(AppDatabase.getDatabase(context).teacherDao())
    }
    override val classRepository: ClassRepository by lazy {
        OfflineClassRepository(AppDatabase.getDatabase(context).classDao())
    }
}