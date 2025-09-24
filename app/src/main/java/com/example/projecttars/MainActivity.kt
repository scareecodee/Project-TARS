package com.example.projecttars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.projecttars.Admin.Login.AdminLogin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            MembersLogin (
//                { username, password ->}
//            )
           AdminLogin (
               { username, password ->}
           )
        }
    }
}

