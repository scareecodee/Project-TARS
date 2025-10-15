package com.example.projecttars.Members.TarsMembers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.graphics.Color
import com.example.projecttars.Members.UiElements.TarsMemberCard
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import com.example.projecttars.Common.FilterType
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.ViewModels.Firebase.MembersVM
import com.example.projecttars.Common.SearchBar

@Composable
fun TarsMembersScreen(
    onViewDetail: (MemberDetail) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddMember: () -> Unit = {},
    membersVM: MembersVM
) {
    BackHandler { onBack() }

    val members by membersVM.members.collectAsState()
    LaunchedEffect(Unit) { membersVM.observeMembers() }

    var selectedFilter by remember { mutableStateOf(FilterType.NAME) }
    var query by remember { mutableStateOf("") }

    val filteredMembers = remember(query, selectedFilter, members) {
        members.filter { member ->
            when (selectedFilter) {
                FilterType.NAME -> member.name?.contains(query, ignoreCase = true) == true
                FilterType.ID -> member.id?.contains(query, ignoreCase = true) == true
                FilterType.DOMAIN -> member.domain?.contains(query, ignoreCase = true) == true
            }
        }
    }

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
        val searchBarSpacing = (screenHeight * 0.015f).dp
        val itemSpacing = (screenHeight * 0.015f).dp
        val noMemberFontSize = (screenWidth * 0.06f).sp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = horizontalPadding, end = horizontalPadding, top = topPadding)
                    .systemBarsPadding()
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
                    text = "Tars Members",
                    fontFamily = FontFamily(Font(R.font.poppinsbold)),
                    color = AccentBlue,
                    fontSize = titleFontSize,
                    modifier = Modifier.weight(1f)
                )

                if (isAdmin) {
                    IconButton(onClick = onAddMember) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Member",
                            tint = AccentBlue,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(searchBarSpacing))

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            Spacer(modifier = Modifier.height(searchBarSpacing))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(itemSpacing),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (filteredMembers.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No Members Found",
                                fontSize = noMemberFontSize,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        }
                    }
                } else {
                    items(filteredMembers) { member ->
                        TarsMemberCard(
                            name = member.name,
                            designation = member.designation,
                            gender = member.gender,
                            domain = member.domain,
                            id = member.id,
                            onViewDetail = { onViewDetail(member) },
                            imageUrl = member.imageUrl
                        )
                    }
                }
            }
        }
    }
}
