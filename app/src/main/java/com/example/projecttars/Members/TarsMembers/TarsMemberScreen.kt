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
import com.example.projecttars.DataModels.TarsMember
import com.example.projecttars.Members.UiElements.TarsMemberCard
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import androidx.compose.material.icons.filled.Add

@Composable
fun TarsMembersScreen(
    tarsMembers: List<TarsMember>,
    onViewDetail: (TarsMember) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddMember: () -> Unit = {}
) {
    BackHandler { onBack() }

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

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(tarsMembers) { tarsMember ->
                TarsMemberCard(
                    name = tarsMember.name,
                    designation = tarsMember.designation,
                    gender = tarsMember.gender,
                    domain = tarsMember.domain,
                    id = tarsMember.id,
                    onViewDetail = { onViewDetail(tarsMember) }
                )
            }
        }
    }
}
