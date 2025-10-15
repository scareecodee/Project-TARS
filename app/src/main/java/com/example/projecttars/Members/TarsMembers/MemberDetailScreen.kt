package com.example.projecttars.Members.TarsMembers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.R
import com.example.projecttars.ViewModels.NavigationData.MemberNavVM
import com.example.projecttars.ui.theme.*

@Composable
fun MemberDetailScreen(
    member: MemberDetail,
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
) {
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizing
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalPadding = (screenHeight * 0.02f).dp
        val cardCornerRadius = (screenWidth * 0.04f).dp
        val cardHeight = (screenHeight * 0.28f).dp
        val headingFontSize = (screenWidth * 0.06f).sp
        val iconSize = (screenWidth * 0.06f).dp
        val sectionTitleFont = (screenWidth * 0.045f).sp
        val sectionContentFont = (screenWidth * 0.035f).sp
        val fabSize = (screenWidth * 0.15f).dp
        val sectionSpacing = (screenHeight * 0.012f).dp
        val rowSpacing = (screenWidth * 0.03f).dp

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                    Spacer(modifier = Modifier.width(rowSpacing))
                    Text(
                        text = "Member Details",
                        color = TextPrimary,
                        fontSize = headingFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }

                if (isAdmin) {
                    IconButton(onClick = { onDeleteClick?.invoke() }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(cardCornerRadius),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .padding(horizontal = horizontalPadding),
                elevation = CardDefaults.cardElevation((screenHeight * 0.015f).dp)
            ) {
                AsyncImage(
                    model = member.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            member.linkedinUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding)
                        .height((screenHeight * 0.065f).dp),
                    shape = RoundedCornerShape(cardCornerRadius)
                ) {
                    Icon(Icons.Default.Link, contentDescription = "LinkedIn", tint = TextPrimary, modifier = Modifier.size(iconSize))
                    Spacer(modifier = Modifier.width(rowSpacing))
                    Text(
                        "LinkedIn Profile",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        fontSize = sectionContentFont
                    )
                }
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = fabSize + verticalPadding)
            ) {
                item { CardSection(Icons.Default.Person, "Name", member.name, screenWidth, screenHeight) }
                item { CardSection(Icons.Default.Domain, "Domain", member.domain, screenWidth, screenHeight) }
                item { CardSection(Icons.Default.Badge, "ID", member.id, screenWidth, screenHeight) }
                item { CardSection(Icons.Default.AccountTree, "Branch", member.branch, screenWidth, screenHeight) }
                item { CardSection(Icons.Default.Work, "Designation", member.designation, screenWidth, screenHeight) }
                item { CardSection(Icons.Default.Build, "Projects", member.projects.joinToString(", "), screenWidth, screenHeight) }
                item { Spacer(modifier = Modifier.height(verticalPadding)) }
            }
        }

        if (isAdmin) {
            FloatingActionButton(
                onClick = { onEditClick?.invoke() },
                containerColor = AccentBlue,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontalPadding, verticalPadding)
                    .size(fabSize)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = TextPrimary, modifier = Modifier.size(iconSize))
            }
        }
    }
}

@Composable
fun CardSection(
    icon: ImageVector,
    title: String,
    content: String,
    screenWidth: Float,
    screenHeight: Float,
    textColor: Color = TextPrimary,
    cardColor: Color = DarkSlate
) {
    val horizontalPadding = (screenWidth * 0.04f).dp
    val verticalPadding = (screenHeight * 0.015f).dp
    val cornerRadius = (screenWidth * 0.04f).dp
    val iconSize = (screenWidth * 0.06f).dp
    val titleFont = (screenWidth * 0.045f).sp
    val contentFont = (screenWidth * 0.035f).sp
    val rowSpacing = (screenWidth * 0.03f).dp
    val sectionSpacing = (screenHeight * 0.012f).dp

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = sectionSpacing)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = AccentOrange, modifier = Modifier.size(iconSize))
            Spacer(modifier = Modifier.width(rowSpacing))
            Column {
                Text(text = title, fontFamily = FontFamily(Font(R.font.poppinsmedium)), color = textColor, fontSize = titleFont)
                Spacer(modifier = Modifier.height(sectionSpacing))
                Text(text = content, fontFamily = FontFamily(Font(R.font.poppinsregular)), color = TextSecondary, fontSize = contentFont)
            }
        }
    }
}
