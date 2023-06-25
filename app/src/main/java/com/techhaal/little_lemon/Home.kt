package com.techhaal.little_lemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.techhaal.little_lemon.MyDestinations.profile
import com.techhaal.little_lemon.ui.theme.MenuItemDao
import com.techhaal.little_lemon.ui.theme.MenuItemRoom
@Composable
fun HomeNavigate(navController: NavHostController) {

    val viewModel: MyViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems()
    val searchPhrase = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }



    Column() {
        Header(navController)
        UpperPanel(){
            searchPhrase.value = it
        }


            LowerPanel(databaseMenuItems, searchPhrase)


    }


}

@Composable
fun Header(navController: NavHostController){
    Column(
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



            }
        }

//


    }
}


@Composable
fun UpperPanel(search : (parameter: String)-> Unit) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    Log.d("AAAAA", "UpperPanel: ${searchPhrase.value}")


        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                search(searchPhrase.value)
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon")
            },

            modifier = Modifier.fillMaxWidth())

    }

//}

@Composable
fun LowerPanel(databaseMenuItems: List<MenuItemRoom>?, search: MutableState<String>) {
    val categories = databaseMenuItems?.map {
        it.category.replaceFirstChar {character ->
            character.uppercase()
        }
    }?.toSet()


    val selectedCategory = remember {
        mutableStateOf("")
    }


    val items = if(search.value == ""){
        databaseMenuItems

    }
    else{
        databaseMenuItems?.filter {
            it.title.contains(search.value, ignoreCase = true)

        }


    }



    val filteredItems = if(selectedCategory.value == "" || selectedCategory.value == "all"){
        items
    }
    else{
        items?.filter {
            it.category.contains(selectedCategory.value, ignoreCase = true)
        }
    }


    Column {
        if (categories != null) {
            MenuCategories(categories) {selectedCategory.value = it
            }
        }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            if (filteredItems != null) {
                for (item in filteredItems){
                    MenuItem(item = item)
                }
            }
        }

    }
}


@Composable
fun MenuCategories(categories: Set<String>, categoryLambda : (sel: String) -> Unit) {
    val cat = remember {
        mutableStateOf("")
    }

    Card( modifier = Modifier.fillMaxWidth()) {

        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Text(text = "ORDER FOR DELIVERY", fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                CategoryButton(category = "All"){
                    cat.value = it.lowercase()
                    categoryLambda(it.lowercase())
                }

                for (category in categories){
                    CategoryButton(category = category){
                        cat.value = it
                        categoryLambda(it)
                    }

                }

            }
        }
    }
}

@Composable
fun CategoryButton(category:String, selectedCategory: (sel: String) -> Unit) {
    val isClicked = remember{
        mutableStateOf(false)
    }
    Button(

        onClick = {
        isClicked.value = !isClicked.value
        selectedCategory(category)

    },
        colors = ButtonDefaults.buttonColors(

            contentColor = Color(0xFFFAFAF9),
            containerColor = Color(0xFF495E57)
           //backgroundColor = Color(0xFD3C8C8)
        )) {
        Text(text = category)
    }
}

//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom) {

    val itemDescription = if(item.description.length>100) {
        item.description.substring(0,100) + ". . ."
    }
    else{
        item.description
    }

    Card(
        modifier = Modifier
            .clickable {

            }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = itemDescription, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "$ ${item.price}", fontWeight = FontWeight.Bold)

            }

//            GlideImage(model = item.imageUrl,
//                contentDescription = "",
//                Modifier.size(100.dp, 100.dp),
//                contentScale = ContentScale.Crop)
        }
    }

}