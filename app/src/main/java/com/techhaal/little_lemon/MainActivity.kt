package com.techhaal.little_lemon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techhaal.little_lemon.MyDestinations.home
import com.techhaal.little_lemon.MyDestinations.onBoarding
import com.techhaal.little_lemon.MyDestinations.profile


class MainActivity : ComponentActivity() {


    //var sharedPref : SharedPreferences = this@MainActivity.getPreferences(Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

//        if (SharedPreferencesHelper.isAppLaunched(context=this)) {

        setContent {
            MyNavigation()
        }
//        } else {
//            SharedPreferencesHelper.setAppLaunched(context=this)
//            Onboarding(navController = )
//        }

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

//fun userDataIsStored(): Boolean {
//
//    return false
//}
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
//fun getUserData(context: Context): Triple<String?, String?, String?> {
//    val sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE)
//    val firstName = sharedPreferences.getString(SharedPreferencesHelper.KEY_FIRST_NAME, null)
//    val lastName = sharedPreferences.getString(SharedPreferencesHelper.KEY_LAST_NAME, null)
//    val email = sharedPreferences.getString(SharedPreferencesHelper.KEY_EMAIL, null)
//    return Triple(firstName, lastName, email)
//}
//
//fun isAppLaunched(context: Context): Boolean {
//    val sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE)
//    return sharedPreferences.getBoolean(SharedPreferencesHelper.KEY_APP_LAUNCHED, false)
//}
//
//fun setAppLaunched(context: Context) {
//    val sharedPreferences = context.getSharedPreferences(SharedPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE)
//    val editor = sharedPreferences.edit()
//    editor.putBoolean(SharedPreferencesHelper.KEY_APP_LAUNCHED, true)
//    editor.apply()
//}
//fun saveUserdata(value: String, value1: String, value2: String, context: Context) {
//    //sharepreferences data save data
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//    val editor = sharedPreferences.edit()
//    editor.putString("firstName", value)
//    editor.putString("lastName", value1)
//    editor.putString("email", value2)
//    editor.apply()
//}
//
//fun getUserData(context: Context): Triple<String?, String?, String?> {
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//    val firstName = sharedPreferences.getString("firstName", null)
//    val lastName = sharedPreferences.getString("lastName", null)
//    val email = sharedPreferences.getString("email", null)
//    return Triple(firstName, lastName, email)
//}


fun mToast(localContext: Context?, value:String){
    Toast.makeText(localContext, value, Toast.LENGTH_LONG).show()
}