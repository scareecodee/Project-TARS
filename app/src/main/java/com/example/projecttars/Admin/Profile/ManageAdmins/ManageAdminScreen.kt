package com.example.projecttars.Admin.Profile.ManageAdmins

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.Members.UiElements.TarsMemberCard
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.AdminVM
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkGrayBlue

@Composable
fun ManageAdminSCreen(
    onBack: () -> Unit,
    onAddAdmin: () -> Unit = {},
    adminVM: AdminVM
) {


    val admins by adminVM.adminEmails.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizes
        val horizontalPadding = (screenWidth * 0.04f).dp
        val topPadding = (screenHeight * 0.02f).dp
        val iconSize = (screenWidth * 0.07f).dp
        val titleFontSize = (screenWidth * 0.065f).sp
        val spacingWidth = (screenWidth * 0.02f).dp
        val itemSpacing = (screenHeight * 0.015f).dp
        val noMemberFontSize = (screenWidth * 0.06f).sp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .systemBarsPadding()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = horizontalPadding, end = horizontalPadding, top = topPadding)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = AccentBlue,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable(onClick = onBack)
                )

                Spacer(modifier = Modifier.width(spacingWidth))

                Text(
                    text = "Manage Admins",
                    fontFamily = FontFamily(Font(R.font.poppinsbold)),
                    color = AccentBlue,
                    fontSize = titleFontSize,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = onAddAdmin) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Member",
                        tint = Color.White,
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(itemSpacing),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontalPadding)
                        .fillMaxWidth()

                ) {
                    if (admins.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "No Admins Found",
                                    fontSize = noMemberFontSize,
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                                )
                            }
                        }
                    } else {
                        items(admins) {admin ->
                         AdminCard(
                             email = admin,
                             adminVM = adminVM,
                         )
                        }
                    }
                }
            }
        }
    }
