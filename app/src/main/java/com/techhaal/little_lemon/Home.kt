package com.techhaal.little_lemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techhaal.little_lemon.MyDestinations.profile

@Composable
fun HomeNavigate(navController: NavHostController) {
    Column(

        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

//             Profile button
            Image(
                painter = painterResource(R.drawable.rename),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigate(profile)
                    }
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Restaurant name: Little Lemon", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("City: Chicago", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Short description: We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                painter = painterResource(R.drawable.hero_image),
//                contentDescription = "Hero Image",
//                modifier = Modifier.fillMaxWidth().height(200.dp),
//                contentScale = ContentScale.Crop
//            )
        }
    }

}