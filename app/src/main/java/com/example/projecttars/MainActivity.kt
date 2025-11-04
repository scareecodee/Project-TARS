package com.example.projecttars
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projecttars.ViewModels.Firebase.AdminVM
import com.example.projecttars.ui.theme.ProjectTARSTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        setContent {
            val navController = rememberAnimatedNavController()
            ProjectTARSTheme(
                activity = this,
                content = {
                    AppNavGraph(navController = navController)
                }
            )
            val adminVM: AdminVM = viewModel()


        }
    }
}
