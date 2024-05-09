package com.example.assignment.data.local

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TeacherViewModel(private val teacherRepository: TeacherRepository) : ViewModel() {
    data class TeacherUiState (
        val id: Int = 0,
        val firstname: String ="",
        val lastname: String ="",
        val password: String ="",
        val gender: String ="",
        val phone: String ="",
        val email: String ="",
        val subject: String =""
    )


    var teacherUiState by mutableStateOf(TeacherUiState()) //Current Ui State
        private set

    init {
        viewModelScope.launch {
            teacherRepository.getAllTeacher().collect { teacherList ->
                _allTeacher.value = teacherList
            }
        }
    }

    private val _allTeacher = MutableStateFlow<List<TeacherEntity>>(emptyList())
    val allTeacher: StateFlow<List<TeacherEntity>> = _allTeacher.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onEditDialog(editTeacher: TeacherEntity) {
        teacherUiState = editTeacher.toTeacherUiState()
        isDialogShown = true
    }

    fun onDelDialog(delTeacher: TeacherEntity) {
        teacherUiState = delTeacher.toTeacherUiState()
        isAlertShown = true
    }
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun TeacherUiState.toTeacherEntity(): TeacherEntity = TeacherEntity(
        id = id,
        firstname = firstname,
        lastname = lastname,
        password = password,
        gender = gender,
        phone = phone,
        email = email,
        subject = subject
    )

    fun TeacherEntity.toTeacherUiState(): TeacherUiState = TeacherUiState(
        id = id,
        firstname = firstname,
        lastname = lastname,
        password = password,
        gender = gender,
        phone = phone,
        email = email,
        subject = subject
    )

    var isDialogShown by mutableStateOf(false)
        private set

    var isTimetableDialogShown by mutableStateOf(false)
        private set
    var isTimetableAlertShown by mutableStateOf(false)
        private set

    var isAlertShown by mutableStateOf(false)
        private set

    var emptyField by mutableStateOf(false)
        private set

    fun onOpenAlertDialog() {
        isAlertShown = true
    }

    fun onCancelDialog() {
        isDialogShown = false
        isAlertShown = false
    }

    fun isTimetableDialogShown(teacher: TeacherEntity) {
        isTimetableDialogShown = true
        isTimetableAlertShown = false
    }

    fun addTeacher(
        firstname: String,
        lastname: String,
        password: String,
        gender: String,
        phone: String,
        email: String,
        subject: String
    ) {
        val newTeacher = TeacherUiState(
            firstname = firstname,
            lastname = lastname,
            password = password,
            gender = gender,
            phone = phone,
            email = email,
            subject = subject
        )
        viewModelScope.launch(Dispatchers.IO) {
            val isEmpty = with(newTeacher) {
                firstname.isEmpty() || lastname.isEmpty() || password.isEmpty() || gender.isEmpty() || phone.isEmpty() || email.isEmpty() || subject.isEmpty()
            }
            if (!isEmpty) {
                teacherRepository.insertTeacher(newTeacher.toTeacherEntity())
                emptyField = false
                isAlertShown = false
            } else {
                isAlertShown = true
                emptyField = true
            }
        }
    }

    fun updateTeacher(updateTeacher: TeacherUiState) {

        viewModelScope.launch(Dispatchers.IO) {
            val isEmpty = with(updateTeacher) {
                firstname.isEmpty() || lastname.isEmpty() || password.isEmpty() || gender.isEmpty() || phone.isEmpty() || email.isEmpty() || subject.isEmpty()
            }
            if (!isEmpty) {
                teacherRepository.updateTeacher(updateTeacher.toTeacherEntity())
                emptyField = false
                isDialogShown = false
            } else {
                isDialogShown = true
                emptyField = true
            }
        }
    }

    fun delTeacher(delTeacher: TeacherUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherRepository.deleteTeacher(delTeacher.toTeacherEntity())
        }
        isAlertShown = false
    }

}