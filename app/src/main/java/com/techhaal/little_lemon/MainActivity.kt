package com.techhaal.little_lemon

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techhaal.little_lemon.MyDestinations.home
import com.techhaal.little_lemon.MyDestinations.onBoarding
import com.techhaal.little_lemon.MyDestinations.profile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json


class MainActivity : ComponentActivity() {
   private suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
        val httpClient = HttpClient(Android){
            install(ContentNegotiation){
                json(contentType = ContentType("text", "plain"))
            }
        }
        val  httpResponse: MenuNetwork = httpClient.get(url).body()
        Log.d("AJSON","this is fetch data ${httpResponse.menu}")
        return httpResponse.menu
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNavigation()
        }
    }
}


@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = if(isUserDataStored(context = LocalContext.current)) home else onBoarding){
        composable(home){
            HomeNavigate(navController)
        }
        composable(profile) {
            ProfileNavigate(navController)
        }
        composable(onBoarding) {
            Onboarding(navController)
        }

    }

}
fun isUserDataStored(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE)
    if(sharedPreferences.contains(SharedPreferencesHelper.KEY_FIRST_NAME)
        && sharedPreferences.contains(SharedPreferencesHelper.KEY_LAST_NAME) && sharedPreferences.contains(
            SharedPreferencesHelper.KEY_EMAIL
        )) {
        return true
    }else
        return false
}
fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(SharedPreferencesHelper.KEY_FIRST_NAME, firstName)
    editor.putString(SharedPreferencesHelper.KEY_LAST_NAME, lastName)
    editor.putString(SharedPreferencesHelper.KEY_EMAIL, email)

    editor.apply()

}
fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun mToast(localContext: Context?, value:String){
    Toast.makeText(localContext, value, Toast.LENGTH_LONG).show()
}