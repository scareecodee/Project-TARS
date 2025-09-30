package com.example.projecttars.Admin.SocialMedia

import androidx.compose.runtime.Composable
import com.example.projecttars.Members.SocialMedia.SocialMediaScreen

@Composable
fun AdminSocialMedia(
    onBackClick: () -> Unit,
    onEditClick: (() -> Unit)? = null
){
    SocialMediaScreen(
        onBackClick = onBackClick,
        isAdmin = true,
        onEditClick = onEditClick
    )
}