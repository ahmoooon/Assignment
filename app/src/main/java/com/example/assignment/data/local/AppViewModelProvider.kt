package com.example.assignment.data.local

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            TeacherViewModel(
                TuitionApplication().container.teacherRepository
            )
        }

        initializer {
            ClassViewModel(
                TuitionApplication().container.classRepository
            )
        }
    }
}

fun CreationExtras.application(): Application =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as Application)