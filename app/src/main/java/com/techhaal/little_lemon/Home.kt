package com.techhaal.little_lemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techhaal.little_lemon.MyDestinations.profile

@Composable
fun HomeNavigate(navController: NavHostController) {
    Column(
//
//        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
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
        Box(
            modifier = Modifier
                .background(Color(0xFF495E57)) // Set the background color of the Box
                 // Occupy the entire available space
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    "Little Lemon",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE0AD12),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Chicago",
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))


                    Row(

                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                        ) {

                        Text(
                            "We are a family-owned \n Mediterranean restaurant, \n focused on traditional \n recipes served  with a \n modern twist",
                            color = Color(0xFFFFFFFF),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(
                            painter = painterResource(R.drawable.hero_image),
                            contentDescription = "Hero Image",
                            modifier = Modifier
                                .width(190.dp)
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )

                    }
                Spacer(modifier = Modifier.height(4.dp))
                Box(

                    modifier = Modifier.height(20.dp).width(200.dp)
                        .background(Color(0xFFA5B8B1)
                            ) ,
                    contentAlignment = Alignment.Center,

                ){
                    Row() {

                    }
                }



            }
        }
        Text(
            "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0E0B05),
            style = MaterialTheme.typography.headlineSmall
        )
        Row(

            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(

                "Starter",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF070500),
                style = MaterialTheme.typography.headlineSmall,
                        fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                "Mains",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF020100),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(40.dp))


            Text(
                "Dessert",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF090600),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(40.dp))

            Text(
                "Size",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF090600),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Text(
            "Greek Salad",
            fontWeight = FontWeight.Bold,

            color = Color(0xFF090600),
            style = MaterialTheme.typography.headlineSmall
        )
        Column(modifier = Modifier.padding(8.dp)) {

            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    "The famous greek salad of crispy\n lettese,peper,olive and our Chigao",
                    color = Color(0xFF090000),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )

            }



        }
    }

}