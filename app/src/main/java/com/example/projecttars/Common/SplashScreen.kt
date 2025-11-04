package com.example.projecttars.Common

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projecttars.ViewModels.Firebase.AuthVM
import kotlinx.coroutines.delay
import com.example.projecttars.R
import com.example.projecttars.ui.theme.DarkSlate

@Composable
fun SplashScreen(
    navController: NavController,
    authVM: AuthVM = viewModel()
) {
    val context = LocalContext.current
    val isLoggedIn by authVM.isLoggedIn.observeAsState(false)
    val userRole by authVM.savedRole.observeAsState("")


    var startAnimation by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = { OvershootInterpolator(2f).getInterpolation(it) })
    )


    LaunchedEffect(Unit) {
        startAnimation = true
        delay(3000)

        if (isLoggedIn) {
            if (userRole == "Admin")
                navController.navigate("AdminMainScreen") { popUpTo("splash") { inclusive = true } }
            else if(userRole=="Members")
                navController.navigate("MembersMainScreen") { popUpTo("splash") { inclusive = true } }
        } else {
            navController.navigate("role_selection") { popUpTo("splash") { inclusive = true } }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkSlate),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.tarsapplogo_foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value)
            )

            Text(
                text = "TARS Connect",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.splashscreenfont))
            )
        }
    }
}
