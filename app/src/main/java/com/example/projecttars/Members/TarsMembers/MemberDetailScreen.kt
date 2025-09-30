package com.example.projecttars.Members.TarsMembers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projecttars.DataModels.MemberDetail



@Composable
fun MemberDetailScreen(
    member: MemberDetail,
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ----- Header -----
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Member Details",
                        color = TextPrimary,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }


                if (isAdmin) {
                    IconButton(onClick = { onDeleteClick?.invoke() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Image(
                    painter = painterResource(id = member.imageResId),
                    contentDescription = member.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            member.linkedinUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Link, contentDescription = "LinkedIn", tint = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "LinkedIn Profile",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item { CardSection(Icons.Default.Person, "Name", member.name) }
                item { CardSection(Icons.Default.Domain, "Domain", member.domain) }
                item { CardSection(Icons.Default.Badge, "ID", member.id) }
                item { CardSection(Icons.Default.AccountTree, "Branch", member.branch) }
                item { CardSection(Icons.Default.Work, "Designation", member.designation) }
                item { CardSection(Icons.Default.Build, "Projects", member.projects.joinToString(", ")) }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }

        if (isAdmin) {
            FloatingActionButton(
                onClick = { onEditClick?.invoke() },
                containerColor = AccentBlue,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = TextPrimary)
            }
        }
    }
}


@Composable
fun CardSection(
    icon: ImageVector,
    title: String,
    content: String,
    textColor: Color = TextPrimary,
    cardColor: Color = DarkSlate
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = AccentOrange, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontFamily = FontFamily(Font(R.font.poppinsmedium)), color = textColor, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = content, fontFamily = FontFamily(Font(R.font.poppinsregular)), color = TextSecondary, fontSize = 14.sp)
            }
        }
    }
}
