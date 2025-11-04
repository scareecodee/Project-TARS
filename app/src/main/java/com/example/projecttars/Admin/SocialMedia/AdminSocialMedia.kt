package com.example.projecttars.Admin.SocialMedia

import androidx.compose.runtime.Composable
import com.example.projecttars.Members.SocialMedia.SocialMediaScreen
import com.example.projecttars.ViewModels.Firebase.TarsSocialViewModel

@Composable
fun AdminSocialMedia(
    onBackClick: () -> Unit,
    onEditClick: (() -> Unit)? = null,
    socialViewModel: TarsSocialViewModel
){
    SocialMediaScreen(
        onBackClick = onBackClick,
        isAdmin = true,
        onEditClick = onEditClick,
        socialViewModel = socialViewModel
    )
}