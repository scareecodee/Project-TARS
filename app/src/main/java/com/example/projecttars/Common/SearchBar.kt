package com.example.projecttars.Common

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkSlate

enum class FilterType {
    NAME, DOMAIN, ID
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
        // Responsive base values based on screen width and height
        val hPadding = maxWidth * 0.025f          // ~10.dp at 400dp width
        val containerHeight = maxHeight * 0.08f    // ~56.dp typical bar height
        val cornerRadius = maxWidth * 0.075f       // ~30.dp at 400dp width
        val borderThickness = maxWidth * 0.00125f  // ~0.5.dp
        val iconSize = maxWidth * 0.06f            // ~24.dp
        val spacerWidth = maxWidth * 0.02f         // ~8.dp
        val textFieldFont = maxWidth.value * 0.035f // ~14.sp
        val placeholderFont = maxWidth.value * 0.03f // ~12.sp
        val dropdownFont = maxWidth.value * 0.037f  // ~15.sp

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = hPadding)
                .height(containerHeight)
                .background(
                    DarkSlate,
                    RoundedCornerShape(cornerRadius)
                )
                .border(
                    borderThickness,
                    AccentBlue,
                    RoundedCornerShape(cornerRadius)
                )
                .padding(horizontal = hPadding)
        ) {
            // Search Icon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = AccentBlue
            )

            Spacer(modifier = Modifier.width(spacerWidth))

            // Search Field
            TextField(
                value = query,
                onValueChange = { onQueryChange(it) },
                placeholder = {
                    Text(
                        when (selectedFilter) {
                            FilterType.NAME -> "Search by name..."
                            FilterType.ID -> "Search by id..."
                            FilterType.DOMAIN -> "Search by domain..."
                        },
                        fontSize = placeholderFont.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsitalic)),
                        color = Color.LightGray
                    )
                },
                modifier = Modifier.weight(1f)
                ,
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
                    fontSize = textFieldFont.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    color = Color.White
                ),
                singleLine = true
            )

            // Filter Dropdown
            Box {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter Icon",
                    tint = AccentBlue,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(color = DarkSlate)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Name",
                                fontSize = dropdownFont.sp,
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
                                "Domain",
                                color = AccentBlue,
                                fontSize = dropdownFont.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        },
                        onClick = {
                            onFilterSelected(FilterType.DOMAIN)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                "ID",
                                color = AccentBlue,
                                fontSize = dropdownFont.sp,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        },
                        onClick = {
                            onFilterSelected(FilterType.ID)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
