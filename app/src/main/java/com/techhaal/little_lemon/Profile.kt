package com.techhaal.little_lemon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Personal Information",
                fontSize = 16.sp,
                style = TextStyle(background = Color(0xFFF7FAF9),
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 40.dp)

            )
            val userData = SharedPreferencesHelper.getUserData(LocalContext.current)
            Text(text = "First name", fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
                        )
            Text(text = " ${userData.first}",
                modifier = Modifier.padding(start = 20.dp)
                    .width(360.dp)
                    .border(2.dp, color = Color(
                        0xFF221F1F
            )
            ))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Last name", fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(text = "${userData.second}",modifier = Modifier.padding(start = 20.dp)
                .width(360.dp)
                .border(2.dp, color = Color(0xFF221F1F))
                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Email", fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(text = " ${userData.third}",modifier = Modifier.padding(start = 20.dp).width(360.dp)
                .border(2.dp, color = Color(0xFF221F1F))
                )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.padding(bottom = 100.dp),
            onClick={

                SharedPreferencesHelper.clearUserData(context)
                navController.navigate(onBoarding)},
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14))
        ){
            Text("Log out",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .width(300.dp)
                    .height(25.dp)
                    .padding(start = 80.dp)
            )

        }

    }

}