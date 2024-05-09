package com.example.assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment.data.local.TeacherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    teacherViewModel: TeacherViewModel,
    teacherDetails: TeacherViewModel.TeacherUiState,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var isExpanded2 by remember {
        mutableStateOf(false)
    }
    var newFirst by remember { mutableStateOf(teacherDetails.firstname) }
    var newLast by remember { mutableStateOf(teacherDetails.lastname) }
    var newPass by remember { mutableStateOf(teacherDetails.password) }
    var newGen by remember { mutableStateOf(teacherDetails.gender) }
    var newPhone by remember { mutableStateOf(teacherDetails.phone) }
    var newEmail by remember { mutableStateOf(teacherDetails.email) }
    var newSub by remember { mutableStateOf(teacherDetails.subject) }

    Dialog(
        onDismissRequest = { onCancel() },
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
                        .height(350.dp)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        OutlinedTextField(
                            value = newFirst, // Use the state value
                            onValueChange = { newFirst = it },
                            label = { Text(text = "First Name") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            },
                            supportingText = {
                                if (newFirst.isEmpty()) {
                                    Text(text = "*required")
                                }
                            },
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newLast, // Use the state value
                            onValueChange = { newLast = it },
                            label = { Text(text = "Last Name") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            },
                            supportingText = {
                                if (newLast.isEmpty()) {
                                    Text(text = "*required")
                                }
                            },
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newPass, // Use the state value
                            onValueChange = { newPass = it },
                            label = { Text(text = "Password") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Lock, contentDescription = null
                                )
                            },
                            supportingText = {
                                if (newFirst.isEmpty()) {
                                    Text(text = "*required")
                                }
                            },
                        )
                    }
                    item {
                        ExposedDropdownMenuBox(
                            expanded = isExpanded,
                            onExpandedChange = { isExpanded = it }) {
                            OutlinedTextField(
                                value = newGen,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                },
                                modifier = Modifier.menuAnchor(),
                                supportingText = {
                                    if (newGen.isEmpty()) {
                                        Text(text = "*required")
                                    }
                                },
                                label = { Text(text = "Gender") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = "Gender"
                                    )
                                }
                            )

                            ExposedDropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false }) {
                                DropdownMenuItem(
                                    text = { Text(text = "Male") },
                                    onClick = {
                                        newGen = "Male"
                                        isExpanded = false
                                    })

                                DropdownMenuItem(
                                    text = { Text(text = "Female") },
                                    onClick = {
                                        newGen = "Female"
                                        isExpanded = false
                                    })
                            }
                        }
                    }
                    item {
                        OutlinedTextField(
                            value = newPhone, // Use the state value
                            onValueChange = { newPhone = it },
                            label = { Text(text = "Phone No.") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Phone, contentDescription = null
                                )
                            },
                            supportingText = {
                                if (newFirst.isEmpty()) {
                                    Text(text = "*required")
                                }
                            },
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newEmail, // Use the state value
                            onValueChange = { newEmail = it },
                            label = { Text(text = "Email") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email, contentDescription = null
                                )
                            },
                            supportingText = {
                                if (newFirst.isEmpty()) {
                                    Text(text = "*required")
                                }
                            },
                        )
                    }
                    item {
                        ExposedDropdownMenuBox(
                            expanded = isExpanded2,
                            onExpandedChange = { isExpanded2 = it }) {
                            OutlinedTextField(
                                value = newSub,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded2)
                                },
                                modifier = Modifier.menuAnchor(),
                                supportingText = {
                                    if (newSub.isEmpty()) {
                                        Text(text = "*required")
                                    }
                                },
                                label = { Text(text = "Subject") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = "Subject"
                                    )
                                }
                            )

                            ExposedDropdownMenu(
                                expanded = isExpanded2,
                                onDismissRequest = { isExpanded2 = false }) {
                                DropdownMenuItem(
                                    text = { Text(text = "BM") },
                                    onClick = {
                                        newSub = "BM"
                                        isExpanded2 = false
                                    })

                                DropdownMenuItem(
                                    text = { Text(text = "SEJ") },
                                    onClick = {
                                        newSub = "SEJ"
                                        isExpanded2 = false
                                    })

                                DropdownMenuItem(
                                    text = { Text(text = "MATHS") },
                                    onClick = {
                                        newSub = "MATHS"
                                        isExpanded2 = false
                                    })

                                DropdownMenuItem(
                                    text = { Text(text = "ENG") },
                                    onClick = {
                                        newSub = "ENG"
                                        isExpanded2 = false
                                    })

                                DropdownMenuItem(
                                    text = { Text(text = "BC") },
                                    onClick = {
                                        newSub = "BC"
                                        isExpanded2 = false
                                    })
                            }
                        }
                    }
                }
                if (teacherViewModel.emptyField) {
                    Text(
                        text = "Please check your input again!",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .width(150.dp)
                        .background(
                            Color(0xFF6650a4),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(onClick = {
                            val updatedTeacher: TeacherViewModel.TeacherUiState = TeacherViewModel.TeacherUiState(
                                id = teacherDetails.id,
                                firstname = newFirst,
                                lastname = newLast,
                                password = newPass,
                                gender = newGen,
                                phone = newPhone,
                                email = newEmail,
                                subject = newSub
                            )
                            teacherViewModel.updateTeacher(updatedTeacher)
                        }
                        ),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = "Confirm",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
fun AlertDialog(
    viewModel: TeacherViewModel,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Confirm to proceed?",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(40.dp))
                if (viewModel.emptyField) {
                    Text(
                        text = "Please check your input again!",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .height(35.dp)
                            .width(125.dp)
                            .background(
                                Color(0xFFD0BCFF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(3.dp, Color(0xFF6650a4), RoundedCornerShape(8.dp))
                            .clickable(onClick = {
                                onCancel()
                            }
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Cancel",
                            color = Color(0xFF6650a4),
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .height(35.dp)
                            .width(125.dp)
                            .background(
                                Color(0xFF6650a4),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable(onClick = {
                                onConfirm()
                            }
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Confirm",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    val viewModel: TeacherViewModel = viewModel()
    AlertDialog(viewModel = viewModel, onConfirm = { /*TODO*/ }, onCancel = { /*TODO*/ })
}

@Preview(showBackground = true)
@Composable
fun CustomDialogPreview(
    viewModel: TeacherViewModel = viewModel()
) {
    val sampleTeacherDetails = listOf(
        TeacherViewModel.TeacherUiState(
            id = 2,
            firstname = "John",
            lastname = "Doe",
            password = "123456",
            gender = "Male",
            phone = "123456",
            email = "john@example.com",
            subject = "Math"
        ),
        TeacherViewModel.TeacherUiState(
            id = 3,
            firstname = "Jane",
            lastname = "Smith",
            password = "654321",
            gender = "Female",
            phone = "654321",
            email = "jane@example.com",
            subject = "Science"
        )
    )

    val selectedTeacherState = sampleTeacherDetails[0]
    CustomDialog(
        teacherViewModel = viewModel,
        teacherDetails = selectedTeacherState,
        onCancel = { /*TODO*/ })
}
