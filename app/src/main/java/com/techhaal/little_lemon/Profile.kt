package com.techhaal.little_lemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techhaal.little_lemon.MyDestinations.onBoarding

@Composable
fun ProfileNavigate(navController: NavHostController) {
    val context = LocalContext.current
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Header()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Personal Information",
            fontSize = 16.sp,
            style = TextStyle(background = Color(0xFF495E57),
                fontWeight = FontWeight.Bold),
            modifier = Modifier.height(100.dp)
                .padding(20.dp)

        )
        val userData = SharedPreferencesHelper.getUserData(LocalContext.current)
        Text(text = "First Name: ${userData.first}")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Last Name: ${userData.second}")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Email: ${userData.third}")
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick={

                SharedPreferencesHelper.clearUserData(context)
                navController.navigate(onBoarding)},
        ){
            Text(text = "Log out",
                fontSize = 16.sp
            )
        }

    }

}