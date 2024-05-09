package com.example.assignment.teacherscreen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment.AlertDialog
import com.example.assignment.BtmNavBar
import com.example.assignment.R
import com.example.assignment.data.local.AppViewModelProvider
import com.example.assignment.data.local.TeacherViewModel

@Composable
fun CreateTeach(
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClicked = onBackClick, "Create Teacher Account")

        Spacer(modifier = Modifier.weight(1f))

        CreateTeachContent()

        Spacer(modifier = Modifier.weight(1f))

        BtmNavBar()
    }

}

@Preview(showBackground = true)
@Composable
fun CreateTeachPreivew() {
    CreateTeach(
        onBackClick = {},
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeachContent(
    teacherViewModel: TeacherViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    var isExpanded by remember { mutableStateOf(false) }
    var isExpanded2 by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .height(550.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            OutlinedTextField(
                value = firstname, // Use the state value
                onValueChange = { firstname = it },
                label = { Text(text = "First Name") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle, contentDescription = null
                    )
                },
                supportingText = {
                    if (firstname.isEmpty()) {
                        Text(text = "*required")
                    }
                },
            )
        }

        item {
            OutlinedTextField(
                value = lastname, // Use the state value
                onValueChange = { lastname = it },
                label = { Text(text = "Last Name") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle, contentDescription = null
                    )
                },
                supportingText = {
                    if (lastname.isEmpty()) {
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
                    value = gender,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    modifier = Modifier.menuAnchor(),
                    supportingText = {
                        if (gender.isEmpty()) {
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
                            gender = "Male"
                            isExpanded = false
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Female") },
                        onClick = {
                            gender = "Female"
                            isExpanded = false
                        })
                }
            }
        }

        item {
            OutlinedTextField(
                value = password, // Use the state value
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock, contentDescription = null
                    )
                },
                supportingText = {
                    if (password.isEmpty()) {
                        Text(text = "*required")
                    }
                },
            )
        }

        item {
            OutlinedTextField(
                value = password2, // Use the state value
                onValueChange = { password2 = it },
                label = { Text(text = "Confirm Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock, contentDescription = null
                    )
                },
                supportingText = {
                    if (password2 != password) {
                        Text(text = "Please fill in the correct password!")
                    }
                },
            )
        }

        item {
            OutlinedTextField(
                value = phone, // Use the state value
                onValueChange = { phone = it },
                label = { Text(text = "Phone No.") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone, contentDescription = null
                    )
                },
                supportingText = {
                    if (phone.isEmpty()) {
                        Text(text = "*required")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }

        item {
            OutlinedTextField(
                value = email, // Use the state value
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email, contentDescription = null
                    )
                },
                supportingText = {
                    if (email.isEmpty()) {
                        Text(text = "*required")
                    }
                },
            )
        }

        item {
            //SUBJECT
            ExposedDropdownMenuBox(
                expanded = isExpanded2,
                onExpandedChange = { isExpanded2 = it }) {
                OutlinedTextField(
                    value = subject,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded2)
                    },
                    modifier = Modifier.menuAnchor(),
                    supportingText = {
                        if (subject.isEmpty()) {
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
                            subject = "BM"
                            isExpanded2 = false
                        })

                    DropdownMenuItem(
                        text = { Text(text = "SEJ") },
                        onClick = {
                            subject = "SEJ"
                            isExpanded2 = false
                        })

                    DropdownMenuItem(
                        text = { Text(text = "MATHS") },
                        onClick = {
                            subject = "MATHS"
                            isExpanded2 = false
                        })

                    DropdownMenuItem(
                        text = { Text(text = "ENG") },
                        onClick = {
                            subject = "ENG"
                            isExpanded2 = false
                        })

                    DropdownMenuItem(
                        text = { Text(text = "BC") },
                        onClick = {
                            subject = "BC"
                            isExpanded2 = false
                        })
                }
            }
        }
    }
    //SUBMIT
    Box(
        modifier = Modifier
            .height(45.dp)
            .width(150.dp)
            .background(
                Color(0xFFBD5683),
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(1.dp)
            .border(1.dp, Color.White)
            .clickable(onClick = { teacherViewModel.onOpenAlertDialog() }),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = "Create",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
    }

    if(teacherViewModel.isAlertShown){
        AlertDialog(
            viewModel = teacherViewModel,
            onConfirm = { teacherViewModel.addTeacher(
                firstname = firstname,
                lastname = lastname,
                password = password,
                gender = gender,
                phone = phone,
                email = email,
                subject = subject)
             },
            onCancel = { teacherViewModel.onCancelDialog()}
        )
    }
}

@Composable
fun TopBar(
    onBackClicked: () -> Unit,
    content: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.weight(0.2f))

        Image(
            painter = painterResource(id = R.drawable.previosbutton),
            contentDescription = "Previous button",
            modifier = Modifier
                .size(22.dp)
                .clickable(onClick = onBackClicked)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = content,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7D5260)
        )

        Spacer(modifier = Modifier.weight(1.5f))
    }
}
