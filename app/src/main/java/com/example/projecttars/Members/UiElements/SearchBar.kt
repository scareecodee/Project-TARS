package com.example.projectz.admin.mainscreen.history

import com.example.projecttars.R
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkSlate

enum class FilterType {
    NAME, AVAILABLE,NOT_AVAILABLE
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    selectedFilter: FilterType,
    onFilterSelected: (FilterType) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    BoxWithConstraints {
        val horizontalPadding = maxWidth * 0.025f
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding/2)
                .height(56.dp)
                .background(
                    DarkSlate,
                    RoundedCornerShape(30.dp)
                )
                .border(0.5.dp, AccentBlue, RoundedCornerShape(30.dp))
                .padding(horizontal = horizontalPadding)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint =AccentBlue
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = query,
                onValueChange = { onQueryChange(it) },
                placeholder = {
                    Text(
                        when (selectedFilter) {
                            FilterType.NAME -> "Search by name..."
                            FilterType.AVAILABLE -> "Search by availability..."
                            FilterType.NOT_AVAILABLE -> "Search by non availability..."
                        },
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsitalic)),
                        color = Color.LightGray
                    )
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DarkSlate,
                    unfocusedContainerColor = DarkSlate,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = AccentBlue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    color = Color.White
                ),
                singleLine = true
            )

            Box {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter Icon",
                    tint = AccentBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(
                            color = DarkSlate,
                        )
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Name",
                                fontSize = 15.sp,
                                color = AccentBlue,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        },
                        onClick = {
                            onFilterSelected(FilterType.NAME)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Available",
                                color = AccentBlue,
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        },
                        onClick = {
                            onFilterSelected(FilterType.AVAILABLE)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Not Available",
                                color = AccentBlue,
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        },
                        onClick = {
                            onFilterSelected(FilterType.NOT_AVAILABLE)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
