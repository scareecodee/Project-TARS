package com.example.projecttars.Members.TarsMembers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.projecttars.Members.UiElements.TarsMemberCard
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
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
    LaunchedEffect(Unit) {
        membersVM.observeMembers()
    }
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(DarkGrayBlue)
            .fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                .systemBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = AccentBlue,
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onBack)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Tars Members",
                fontFamily = FontFamily(Font(R.font.poppinsbold)),
                color = AccentBlue,
                fontSize = 25.sp,
                modifier = Modifier.weight(1f) // pushes + to right
            )


            if (isAdmin) {
                IconButton(onClick = onAddMember) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Member",
                        tint = AccentBlue
                    )
                }
            }
        }

       SearchBar(
           query = query,
           onQueryChange = { query = it },
           selectedFilter = selectedFilter,
           onFilterSelected = { selectedFilter = it }
       )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if(filteredMembers.isEmpty()){
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            "No Members Found",
                            fontSize = 25.sp,
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
