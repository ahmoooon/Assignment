package com.example.assignment.timetablescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment.R
import com.example.assignment.data.local.AppViewModelProvider
import com.example.assignment.data.local.TeacherEntity
import com.example.assignment.data.local.TeacherViewModel
import com.example.assignment.teacherscreen.TopBar
import androidx.compose.foundation.lazy.items
import com.example.assignment.data.local.ClassViewModel

@Preview (showBackground = true)
@Composable
fun CreateTimetablePreview(){
    CreateTimetable(onBackClicked = {})
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateTimetable(
    teacherViewModel: TeacherViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val searchText by teacherViewModel.searchText.collectAsState()
    val allTeacher by teacherViewModel.allTeacher.collectAsState()

    val tabItems = listOf(
        TabItem(title = "Teacher"),
        TabItem(title = "Student")
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClicked = onBackClicked, content = "Create Timetable For")
        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .weight(9f)
                    .border(3.dp, color = Color(0xffEECDE2), shape = CircleShape)
                    .clip(CircleShape)
            ) {
                tabItems.forEachIndexed { index, item ->
                    CustomTab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        title = item.title
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top

        ) { index ->
            if (tabItems[index].title == "Teacher") {
                TeachTimetable(
                    searchText = searchText,
                    onValueChange = teacherViewModel::onSearchTextChange,
                    allTeacher = allTeacher
                )
            } else {
//                StudentTimetable(
//                    searchText = searchText,
//                    onValueChange = teacherViewModel::onSearchTextChange,
//                    persons = persons
//                )
            }
        }
    }
}



@Composable
fun CustomTab(
    selected: Boolean,
    onClick: () -> Unit,
    title: String
) {
    Box(
        modifier = Modifier
            .background(
                color = if (selected) Color(0xffEECDE2) else Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
}
data class TabItem(
    val title: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeachTimetable(
    searchText: String,
    onValueChange: (String) -> Unit,
    allTeacher: List<TeacherEntity>,
    teacherViewModel: TeacherViewModel = viewModel(factory = AppViewModelProvider.Factory),
    classViewModel: ClassViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var value by remember {
        mutableStateOf(searchText)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            placeholder = { Text(text = "Search...") },
            modifier = Modifier
                .height(50.dp)
                .width(330.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        val filteredTeachers = allTeacher.filter { teacher ->
            val fullName = "${teacher.firstname} ${teacher.lastname}"
            fullName.contains(searchText, ignoreCase = true)
        }

        LazyColumn {
            items(filteredTeachers) { teacher ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(300.dp)
                            .background(
                                color = Color(0xFFF9EBF4),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clickable { teacherViewModel.isTimetableDialogShown(teacher) },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(0.1f))

                            Image(
                                painter = painterResource(id = R.drawable.profilepicture),
                                contentDescription = "Profile",
                                modifier = Modifier.size(40.dp)
                            )

                            Spacer(modifier = Modifier.weight(0.2f))

                            Text(text = "${teacher.firstname} ${teacher.lastname}",
                                fontSize = 15.sp
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }


    if (teacherViewModel.isTimetableDialogShown) {
        Dialog(
            onDismissRequest = { teacherViewModel.onCancelDialog() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .border(1.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(15.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {

                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTimetableDialog (time: String, expanded: Boolean, classroom: String){

    var isExpanded = expanded
    var newClassroom = classroom

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it })
    {
        OutlinedTextField(
            value = newClassroom,
            onValueChange = {  },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor(),

            label = { Text(text = time) },
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(
                text = { Text(text = "No class") },
                onClick = {
                    newClassroom = "No Class"
                    isExpanded = false
                }
            )

            DropdownMenuItem(
                text = { Text(text = "A2") },
                onClick = {
                    newClassroom = "A2"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "A3") },
                onClick = {
                    newClassroom = "A3"
                    isExpanded = false
                }
            )

            DropdownMenuItem(
                text = { Text(text = "A4") },
                onClick = {
                    newClassroom = "A4"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "A5") },
                onClick = {
                    newClassroom = "A5"
                    isExpanded = false
                }
            )

            DropdownMenuItem(
                text = { Text(text = "B1") },
                onClick = {
                    newClassroom = "B1"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "B2") },
                onClick = {
                    newClassroom = "B2"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "B3") },
                onClick = {
                    newClassroom = "B3"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "B4") },
                onClick = {
                    newClassroom = "B4"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "B5") },
                onClick = {
                    newClassroom = "B5"
                    isExpanded = false
                }
            )
        }
    }
}