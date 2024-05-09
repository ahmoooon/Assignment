package com.example.assignment.teacherscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment.AlertDialog
import com.example.assignment.BtmNavBar
import com.example.assignment.CustomDialog
import com.example.assignment.R
import com.example.assignment.data.local.AppViewModelProvider
import com.example.assignment.data.local.TeacherViewModel


@Composable
fun EditTeach(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        TopBar(onBackClicked = onBackClicked, content = "Edit Teacher List")

        Spacer(modifier = Modifier.weight(0.03f))

        TeachSearchBar()

        Spacer(modifier = Modifier.weight(1f))

        BtmNavBar()
    }
}


@Preview(showBackground = true)
@Composable
fun EditTeachPreview() {
    EditTeach(
        onBackClicked = {}
    )
}

@Composable
fun AllData(
    onEditClick: () -> Unit,
    onDelClick: () -> Unit,
    name: String
) {
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
                ),
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

                Text(
                    text = name,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .clickable(onClick = { onEditClick() }),
                )

                Spacer(modifier = Modifier.weight(0.1f))

                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .clickable(onClick = { onDelClick() }),
                )

                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    }
}

@Composable
fun TeachSearchBar(
    teacherViewModel: TeacherViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val searchText by teacherViewModel.searchText.collectAsState()
    val persons by teacherViewModel.allTeacher.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = teacherViewModel::onSearchTextChange,
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

        LazyColumn {
            val filteredPersons = persons.filter { person ->
                val fullName = "${person.firstname} ${person.lastname}"
                fullName.contains(searchText, ignoreCase = true)
            }

            items(filteredPersons) { person ->
                AllData(
                    name = "${person.firstname} ${person.lastname}",
                    onEditClick = { teacherViewModel.onEditDialog(person)},
                    onDelClick = { teacherViewModel.onDelDialog(person)}
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        if (teacherViewModel.isDialogShown) {
            CustomDialog(
                onCancel = {
                    teacherViewModel.onCancelDialog()
                },
                teacherDetails = teacherViewModel.teacherUiState,
                teacherViewModel = teacherViewModel
            )
        }

        if (teacherViewModel.isAlertShown){
            AlertDialog(
                viewModel = teacherViewModel,
                onConfirm = { teacherViewModel.delTeacher(teacherViewModel.teacherUiState) },
                onCancel = { teacherViewModel.onCancelDialog() })
        }
    }
}



