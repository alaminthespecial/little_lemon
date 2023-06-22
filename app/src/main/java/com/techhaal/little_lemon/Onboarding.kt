package com.techhaal.little_lemon

// Step 1: Create a new file called Onboarding.kt

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
//import androidx.compose.material.Button
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techhaal.little_lemon.MyDestinations.home

//import androidx.compose.ui.tooling.preview.PreviewValueProvider

// Step 2: Create the Onboarding Composable
@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current
    //val context: Context? =null
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step 2: Add the Header
        Spacer(modifier = Modifier.height(20.dp))
        Header()
        Spacer(modifier = Modifier.height(40.dp))
        // Step 4: Collect user input
        Text(
            text = "Let's get to know you",
            fontSize = 16.sp,
            style = TextStyle(background = Color(0xFF495E57)),
            modifier = Modifier.padding(16.dp).fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Personal Information",
            fontSize = 16.sp,
            style = TextStyle(background = Color(0xFF495E57),
                fontWeight = FontWeight.Bold),
            modifier = Modifier.height(100.dp)
                .padding(20.dp)

        )
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }

        TextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(100.dp))
        // Step 5: Add a register button
        Button(
            onClick = {
                if(firstName.value.isBlank()||lastName.value.isBlank()||email.value.isBlank()){
                    // mToast(context,"All data is filed Registered ")
                    showMessage(context, message = "Registration unsuccessful. Please enter all data")
                }else{
                    saveUserData(context, firstName.value,lastName.value,email.value)
                    navController.navigate(home)
                    showMessage(context, message = "Registration successful!")
                }
            },
            modifier = Modifier.width(100.dp)
                .height(50.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14))
        ) {
            Text(text = "Register")
        }
    }
}



// Step 2: Create the Onboarding Composable preview
//@Preview
//@Composable
//fun OnboardingPreview() {
//    Onboarding(navController)
//}

// Step 2: Implement the Header Composable
@Composable
fun Header() {
    // val logo = painterResource(R.drawable.logo)
//    Image(
//        painter = logo,
//        contentDescription = "Logo",
//        modifier = Modifier.fillMaxWidth().height(100.dp)
//            .padding(16.dp)
//    )
}