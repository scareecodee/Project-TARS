package com.example.projecttars.Admin.Profile.ManageAdmins


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.ViewModels.Firebase.AdminVM
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.R
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAdminScreen(
    adminVM: AdminVM,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Admin", color = AccentBlue, fontFamily = FontFamily(Font(R.font.poppinsbold)), fontSize =25.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AccentBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkGrayBlue
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGrayBlue)
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Admin Email", color =Color.White, fontFamily = FontFamily(Font(R.font.poppinsmedium))) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = DarkSlate,
                        unfocusedContainerColor = DarkSlate,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = AccentBlue,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (email.isNotBlank()) {
                            showConfirmation = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                ) {
                    Text("Save", color = Color.White, fontFamily = FontFamily(Font(R.font.poppinsmedium)))
                }
            }
        }
    )


    if (showConfirmation) {
        AlertDialog(
            onDismissRequest = { showConfirmation = false },
            title = { Text("Confirm Add Admin", color = Color.White, fontFamily = FontFamily(Font(R.font.poppinsmedium))) },
            text = { Text("Are you sure you want to add $email as an admin?", color= TextSecondary, fontFamily = FontFamily(Font(R.font.poppinsmedium))) },
            confirmButton = {
                TextButton(
                    onClick = {
                        adminVM.addAdmin(email.lowercase(Locale.getDefault()), onResult = {result->
                            if (result) {
                                Toast.makeText(context, "Admin added successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                            }
                        })
                        onBack()
                    }
                ) {
                    Text("Yes", color = Color.White, fontFamily = FontFamily(Font(R.font.poppinsmedium)))
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmation = false }) {
                    Text("No", color = Color.White, fontFamily = FontFamily(Font(R.font.poppinsmedium)))
                }
            }
        )
    }
}
