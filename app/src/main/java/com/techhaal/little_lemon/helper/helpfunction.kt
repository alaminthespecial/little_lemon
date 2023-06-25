package com.techhaal.little_lemon.helper

import android.util.Log
import com.techhaal.little_lemon.MenuItemNetwork
import com.techhaal.little_lemon.MenuNetwork
import com.techhaal.little_lemon.ui.theme.AppDatabase
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
        val httpClient = HttpClient(Android){
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Text.Plain
                )

                engine {
                    connectTimeout = 60_000
                    socketTimeout = 60_000
                }
            }
           install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }
        }
        val  httpResponse: MenuNetwork = httpClient.get(url).body()
       Log.d("AJSON","this is fetch data ${httpResponse.menu}")
      return httpResponse.menu
  }


  fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {


      val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }