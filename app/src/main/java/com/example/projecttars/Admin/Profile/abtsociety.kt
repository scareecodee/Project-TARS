package com.example.projecttars.ui.screens

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.AccentPurple
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.ui.theme.DividerGray
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary

@Composable
fun AboutSocietyScreen(
    onBackClick: () -> Unit
) {


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
    ) {
        val screenWidth = maxWidth
        val isLargeScreen = screenWidth > 600.dp

        val logoSize = if (isLargeScreen) 160.dp else 120.dp
        val horizontalPadding = if (isLargeScreen) 32.dp else 16.dp
        val titleFontSize = if (isLargeScreen) 26.sp else 22.sp
        val subtitleFontSize = if (isLargeScreen) 18.sp else 16.sp
        val textFontSize = if (isLargeScreen) 17.sp else 15.sp

        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier.systemBarsPadding()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AccentBlue,
                        modifier =Modifier
                            .size(25.dp)
                    )
                }

                Text(
                    text = "About TARS Society",
                    color = AccentBlue,
                    fontSize = 22.sp,
                   fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinsbold)),

                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    painter = painterResource(id = R.drawable.tarsapplogo_foreground),
                    contentDescription = "TARS Logo",
                    modifier = Modifier
                        .size(logoSize),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tech & Automation Robotics Society",
                    color = TextPrimary,
                    fontSize = titleFontSize,
                    fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinsbold)),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Innovating the Future of Technology",
                    color = AccentOrange,
                    fontSize = subtitleFontSize,
                    fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinsitalic)),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = "TARS Society is a student-led initiative aimed at fostering innovation in technology, automation, and robotics. We focus on building real-world projects, hosting workshops, and collaborating on research in the fields of AI, embedded systems, and IoT.",
                    color = TextSecondary,
                    fontSize = textFontSize,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinsregular)),
                )

                Spacer(modifier = Modifier.height(28.dp))

                Divider(color = DividerGray, thickness = 1.dp)

                Spacer(modifier = Modifier.height(28.dp))


                InfoCard(
                    background = DarkSlate,
                    textColor = TextPrimary,
                    accentBlue = AccentBlue,
                    accentOrange = AccentOrange,
                    accentPurple = AccentPurple,
                    fontSize = textFontSize
                )
            }
        }
    }
}

@Composable
fun InfoCard(
    background: Color,
    textColor: Color,
    accentBlue: Color,
    accentOrange: Color,
    accentPurple: Color,
    fontSize: androidx.compose.ui.unit.TextUnit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(background, RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        InfoRow(label = "Founded", value = "2018", color = accentBlue, fontSize = fontSize)
        InfoRow(label = "Members", value = "30+", color = accentOrange, fontSize = fontSize)
        InfoRow(label = "Domains", value = "AI/Ml • Robotics • IoT • VLSI/Embedded Systems ", color = accentPurple, fontSize = fontSize)
    }
}

@Composable
fun InfoRow(label: String, value: String, color: Color, fontSize: androidx.compose.ui.unit.TextUnit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start)
    ) {
        Text(
            text = label,
            fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinssemibold)),
            color = Color.White.copy(alpha = 0.85f),
            fontSize = fontSize
        )
        Text(
            text = value,
            color = color,
            fontFamily = androidx.compose.ui.text.font.FontFamily(Font(R.font.poppinssemibold)),
            fontSize = fontSize
        )
    }
}
