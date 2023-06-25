package com.techhaal.little_lemon

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.techhaal.little_lemon.helper.fetchMenu
import com.techhaal.little_lemon.helper.saveMenuToDatabase
import com.techhaal.little_lemon.ui.theme.AppDatabase
import com.techhaal.little_lemon.ui.theme.MenuItemRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val database: AppDatabase

    init {
        database = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database"
        ).allowMainThreadQueries().build()
    }

    fun getAllDatabaseMenuItems(): List<MenuItemRoom> {
        return database.menuItemDao().getAll()
    }

    fun fetchMenuDataIfNeeded() {
      viewModelScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {

                saveMenuToDatabase(
                    database,
                    fetchMenu("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                )
               // Log.d("Save Data ", "${database.menuItemDao().getAll()}")
            }
        }
    }

}