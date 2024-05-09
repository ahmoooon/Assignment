package com.example.assignment

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuperHome(
    createTeacherClick: () -> Unit,
    editTeacherClick: () -> Unit,

    createParentClick: () -> Unit,
    editParentClick: () -> Unit,

    createTimetable: () -> Unit,
    editTimetable: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ProfileSec()

        com.example.assignment.DropdownMenu(
            createTeacherClick = createTeacherClick,
            editTeacherClick = editTeacherClick,
            createParentClick = createParentClick,
            editParentClick = editParentClick,
            createTimetable = createTimetable,
            editTimetable = editTimetable
        )

        Spacer(modifier = Modifier.weight(1f))
        BtmNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHomePreview() {
    SuperHome(
        createTeacherClick = { /*TODO*/ },
        editTeacherClick = { /*TODO*/ },
        createParentClick = { /*TODO*/ },
        editParentClick = { /*TODO*/ },
        createTimetable = { /*TODO*/ },
        editTimetable = { /*TODO*/ })
}

@Composable
fun MenuItem(name: String, onItemClicked: () -> Unit, desc: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFFF9EBF4)
            )
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onItemClicked
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = String.format("%s %s", desc, name),
                color = Color(0xFFBD5683),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun DropdownMenu(
    createTeacherClick: () -> Unit,
    editTeacherClick: () -> Unit,

    createParentClick: () -> Unit,
    editParentClick: () -> Unit,

    createTimetable: () -> Unit,
    editTimetable: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(0) }
    val list = listOf("Teacher Account", "Parent Account", "Timetable")
    Column {

        Box(
            modifier = Modifier
                .background(color = Color(0xFFBD5683), shape = RectangleShape)
                .fillMaxWidth()
                .height(40.dp),
        )
        {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.weight(1.35f))

                Text(
                    text = list[selectedItem],
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painterResource(R.drawable.dropdownbutton),
                    contentDescription = "Drop Down Button",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            isExpanded = true
                        }
                )

                Spacer(modifier = Modifier.weight(0.1f))

                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier
                        .background(color = Color(0xFFBD5683), shape = RectangleShape)
                        .fillMaxWidth()
                )
                {
                    list.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = text,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.Transparent,
                                            shape = RectangleShape
                                        )
                                )
                            },
                            modifier = Modifier.height(40.dp),
                            onClick = {
                                selectedItem = list.indexOf(text)
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }

    val desc = listOf("Create", "Edit", "Delete")

    if (selectedItem == 0) {
        Column {
            MenuItem(name = list[selectedItem], onItemClicked = createTeacherClick, desc[0])
            MenuItem(name = list[selectedItem], onItemClicked = editTeacherClick, desc[1])
        }
    } else if (selectedItem == 1) {
        MenuItem(name = list[selectedItem], onItemClicked = createParentClick, desc[0])
        MenuItem(name = list[selectedItem], onItemClicked = editParentClick, desc[1])
    } else {
        MenuItem(name = list[selectedItem], onItemClicked = createTimetable, desc[0])
        MenuItem(name = list[selectedItem], onItemClicked = editTimetable, desc[1])
    }
}


@Composable
//Profile
fun ProfileSec() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        Image(
            painterResource(R.drawable.profilepicture),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .weight(4f)
                .clip(CircleShape)
                .clickable { }
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(modifier = Modifier.weight(7f), horizontalAlignment = Alignment.Start) {
            Text(
                text = "Welcome, superuser",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF7D5260)
            )
            Box(
                modifier = Modifier
                    .clickable { }
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6650a4),
                    modifier = Modifier
                        .offset(1.dp, 1.dp)
                        .blur(1.dp)
                )
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6650a4),
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}

//Nav Bar
@Composable
fun BtmNavBar() {
    //Bottom nav bar
    Box(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .shadow(1.dp, ambientColor = Color.Black)
            .background(
                color = Color.LightGray,
                shape = RectangleShape
            ),
    )

    Spacer(modifier = Modifier.height(5.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { }
        ) {
            Image(
                painterResource(R.drawable.home),
                contentDescription = "home",
                modifier = Modifier.size(39.dp)
            )
            Text(text = "Home", fontSize = 11.sp, color = Color(0xff5b5b5b))
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { }
        ) {
            Image(
                painterResource(R.drawable.teacher),
                contentDescription = "teacher",
                modifier = Modifier.size(39.dp)
            )
            Text(text = "Teacher", fontSize = 11.sp, color = Color(0xff5b5b5b))
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { }
        ) {
            Image(
                painterResource(R.drawable.parent),
                contentDescription = "parent",
                modifier = Modifier.size(39.dp)
            )
            Text(text = "Parent", fontSize = 11.sp, color = Color(0xff5b5b5b))
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
    Spacer(modifier = Modifier.height(5.dp))
}
