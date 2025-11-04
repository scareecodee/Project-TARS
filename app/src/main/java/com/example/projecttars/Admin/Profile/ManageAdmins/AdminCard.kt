package com.example.projecttars.Admin.Profile.ManageAdmins

import android.graphics.fonts.Font
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.ViewModels.Firebase.AdminVM
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary
import com.example.projecttars.R

@Composable
fun AdminCard(
    email: String,
    adminVM: AdminVM,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSlate),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val cardWidth = maxWidth

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val textSize = if (cardWidth < 300.dp) 14.sp else 16.sp

                Text(
                    text = email,
                    color = TextPrimary,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Admin",
                        tint = AccentOrange
                    )
                }
            }
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Admin", color = TextPrimary,fontSize = 17.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            text = { Text("Are you sure you want to delete $email?", color = TextSecondary,       fontSize = 12.sp,
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
            confirmButton = {
                TextButton(
                    onClick = {
                        adminVM.deleteAdmin(email,
                            onResult = {
                                if (it) {
                                    Toast.makeText(context, "Admin deleted successfully", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                                }
                            }

                        )
                        showDeleteDialog = false
                    }
                ) {
                    Text("Delete", color =Color.White,fontSize = 12.sp,
                        fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel", color = TextPrimary, fontSize = 12.sp,
                        fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                }
            },
            containerColor = DarkGrayBlue
        )
    }
}
