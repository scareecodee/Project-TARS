import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkCharcoal
import com.example.projecttars.ui.theme.TextPrimary
import com.example.projecttars.ui.theme.TextSecondary



@Composable
fun AdminResCard(
    imageUrl: String,
    componentName: String,
    isAvailable: Boolean,
    onClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Screen dimensions
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // Responsive sizes based on screen width
        val cardHeight = maxOf(screenWidth * 0.6f, 200.dp)
        val cornerRadius = screenWidth * 0.04f
        val elevation = screenWidth * 0.01f
        val availabilityBarHeight = screenWidth * 0.025f
        val textVerticalPadding = screenWidth * 0.015f
        val bottomPadding = screenWidth * 0.03f
        val iconSize = screenWidth * 0.065f
        val spacerWidth = screenWidth * 0.02f
        val fontSizeName = screenWidth * 0.07f
        val fontSizeAvailability = screenWidth * 0.055f

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clip(RoundedCornerShape(cornerRadius))
                .clickable(onClick = onClick),
            colors = CardDefaults.cardColors(containerColor = DarkCharcoal),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // Availability indicator bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(availabilityBarHeight)
                        .background(if (isAvailable) AccentBlue else AccentOrange)
                )

                // Component Image
                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        filterQuality = androidx.compose.ui.graphics.FilterQuality.High,
                        model = imageUrl,
                        contentDescription = componentName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tarslogo),
                        contentDescription = "Default Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }

                // Component name
                Text(
                    text = componentName,
                    fontSize = fontSizeName.value.sp,
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = textVerticalPadding)
                )

                // Availability row
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = bottomPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isAvailable) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = if (isAvailable) "Available" else "Not Available",
                        tint = if (isAvailable) Color.Green.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.5f),
                        modifier = Modifier.size(iconSize)
                    )
                    Spacer(modifier = Modifier.width(spacerWidth))
                    Text(
                        text = if (isAvailable) "Available" else "Not Available",
                        color = TextSecondary,
                        fontSize = fontSizeAvailability.value.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsitalic))
                    )
                }
            }
        }
    }
}
