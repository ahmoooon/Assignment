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
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch


class ClassViewModel(private val classRepository: ClassRepository) : ViewModel() {
    data class ClassUiState(
        val classId: Int = 0,
        val dayOfWeek: Int = 0,
        val time: String = "",
        val classroom: String = "",
        val grade: Int = 0,
        val teacherId: Int = 0
    )


    var classUiState by mutableStateOf(ClassUiState()) //Current Ui State
        private set

    init {
        viewModelScope.launch {
            classRepository.getAllClass().collect { classList ->
                _allClass.value = classList
            }
        }
    }

    private val _allClass = MutableStateFlow<List<ClassEntity>>(emptyList())
    val allClass: StateFlow<List<ClassEntity>> = _allClass.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onEditDialog(editClass: ClassEntity) {
        classUiState = editClass.toClassUiState()
        isDialogShown = true
    }

    fun onDelDialog(delClass: ClassEntity) {
        classUiState = delClass.toClassUiState()
        isAlertShown = true
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun ClassUiState.toClassEntity(): ClassEntity = ClassEntity(
        classId = classId,
        dayOfWeek = dayOfWeek,
        time = time,
        classroom = classroom,
        grade = grade,
        teacherId = teacherId

    )

    fun ClassEntity.toClassUiState(): ClassUiState = ClassUiState(
        classId = classId,
        dayOfWeek = dayOfWeek,
        time = time,
        classroom = classroom,
        grade = grade,
        teacherId = teacherId
    )

    var isDialogShown by mutableStateOf(false)
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

    fun checkDayOfWeek(): List<String> {
        val dayOfWeekOptions: List<String> = listOf()
        var x = ""
        for (i in 1..5) {
            if (classRepository.getCountByDayOfWeek(i) != 30) {
                x = when (i) {
                    1 -> "Monday"
                    2 -> "Tuesday"
                    3 -> "Wednesday"
                    4 -> "Thursday"
                    else -> "Friday"
                }
                dayOfWeekOptions + x
            }
//            else
//                noMoreOptions = true
        }
        return dayOfWeekOptions
    }

    fun checkTimeslot(dayChosenString: String): List<String> {
        val timeslotOptions: List<String> = listOf()
        val allTimeslots: List<String> = listOf("8:30AM-9:30AM", "9:45AM-11:15AM", "2:30PM-4:00PM", "4:15PM-5:45PM", "6:0PM-7:30PM", "8:00PM-9:30PM")
        var x = ""
        val dayChosen = when(dayChosenString){
            "Monday" -> 1
            "Tuesday" -> 2
            "Wednesday" -> 3
            "Thursday" -> 4
            else -> 5
        }
        for (i in 1..6){
            if(classRepository.getCountByDayOfWeekAndTime(dayChosen,allTimeslots[i]) != 5){
                x = allTimeslots[i]
                timeslotOptions + x
            }
        }
        return timeslotOptions
    }

    fun checkClassroom(dayChosenString: String, timeChosen: String): List<String>{
        val classroomOptions: List<String> = listOf()
        val dayChosen = when(dayChosenString){
            "Monday" -> 1
            "Tuesday" -> 2
            "Wednesday" -> 3
            "Thursday" -> 4
            else -> 5
        }
        val allClassroom: List<String> = listOf("A1","A2","A3","A4","A5")
        val occupiedClassroom: List<String> = classRepository.getClassByDayOfWeekAndTimeAndClassroom(dayChosen, timeChosen)
        var x = 0

        for(classrooms in allClassroom) {
            for (classroom in occupiedClassroom) {
                if(classrooms == classroom)
                    x++
            }
            if (x == 0){
                classroomOptions + classrooms
            }
        }

        return classroomOptions
    }

    fun addClass(
        dayOfWeek: Int,
        time: String,
        classroom: String,
        grade: Int,
        teacherId: Int
    ) {
        val newClass = ClassUiState(
            dayOfWeek = dayOfWeek,
            time = time,
            classroom = classroom,
            grade = grade,
            teacherId = teacherId
        )
        viewModelScope.launch(Dispatchers.IO) {
            val isEmpty = with(newClass) {
                dayOfWeek == 0|| time.isEmpty() || classroom.isEmpty() || grade == 0
            }
            if (!isEmpty) {
                classRepository.insertClass(newClass.toClassEntity())
                emptyField = false
                isAlertShown = false
            } else {
                isAlertShown = true
                emptyField = true
            }
        }
    }

    fun updateClass(updateClass: ClassUiState) {

        viewModelScope.launch(Dispatchers.IO) {
            val isEmpty = with(updateClass) {
                dayOfWeek == 0|| time.isEmpty() || classroom.isEmpty() || grade == 0
            }
            if (!isEmpty) {
                classRepository.updateClass(updateClass.toClassEntity())
                emptyField = false
                isDialogShown = false
            } else {
                isDialogShown = true
                emptyField = true
            }
        }
    }

    fun delClass(delClass: ClassUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            classRepository.deleteClass(delClass.toClassEntity())
        }
        isAlertShown = false
    }

}