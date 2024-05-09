package com.example.assignment.parentscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.BtmNavBar
import com.example.assignment.teacherscreen.TopBar

@Composable
fun CreateParent(
    onCreateParentClicked: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClicked = onBackClick, "Create Parent Account")

        Spacer(modifier = Modifier.weight(0.5f))

        CreateParentContent(onCreateParentClicked = onCreateParentClicked)

        Spacer(modifier = Modifier.weight(1f))

        BtmNavBar()
    }
}

@Preview (showBackground = true)
@Composable
fun CreateParentPreview(){
    CreateParent(
        onCreateParentClicked = { /*TODO*/ },
        modifier = Modifier,
        onBackClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateParentContent(
    onCreateParentClicked: () -> Unit
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //FIRST NAME
        OutlinedTextField(
            value = firstname, // Use the state value
            onValueChange = { firstname = it },
            label = { Text(text = "First Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle, contentDescription = "First Name")
            },
            supportingText = {
                if (firstname.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )
        //LAST NAME
        OutlinedTextField(
            value = lastname, // Use the state value
            onValueChange = { lastname = it },
            label = { Text(text = "Last Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle, contentDescription = "Last Name")
            },
            supportingText = {
                if (lastname.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )


        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it}) {
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
                label = { Text(text = "Gender")},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "Gender")
                }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Male") },
                    onClick = {
                        gender = "Male"
                        isExpanded = false})

                DropdownMenuItem(
                    text = { Text(text = "Female") },
                    onClick = {
                        gender = "Female"
                        isExpanded = false})
            }
        }

        //PASSWORD
        OutlinedTextField(
            value = password, // Use the state value
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock, contentDescription = "Password")
            },
            supportingText = {
                if (password.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )

        //CONFIRM PASSWORD
        OutlinedTextField(
            value = password2, // Use the state value
            onValueChange = { password2 = it },
            label = { Text(text = "Confirm Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock, contentDescription = "Password")
            },
            supportingText = {
                if (password2.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )

        //PHONE
        OutlinedTextField(
            value = phone, // Use the state value
            onValueChange = { phone = it },
            label = { Text(text = "Phone No.") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Phone, contentDescription = "Phone")
            },
            supportingText = {
                if (phone.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )

        //EMAIL
        OutlinedTextField(
            value = email, // Use the state value
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email, contentDescription = "Phone")
            },
            supportingText = {
                if (email.isEmpty()) {
                    Text(text = "*required")
                }
            },
        )

        Spacer(modifier = Modifier.height(15.dp))

        //SUBMIT
        Box(modifier = Modifier
            .height(45.dp)
            .width(150.dp)
            .background(
                Color(0xFFBD5683),
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(1.dp)
            .border(1.dp, Color.White)
            .clickable(onClick = onCreateParentClicked),
            contentAlignment = Alignment.Center)
        {
            Text(
                text = "Create",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
