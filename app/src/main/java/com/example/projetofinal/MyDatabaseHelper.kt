package com.example.projetofinal

import android.util.Log
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "NightmaresDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE = """
            CREATE TABLE NightmaresTable (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                date TEXT,
                description TEXT,
                terrorRating INTEGER,
                tags TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS NightmaresTable")
        onCreate(db)
    }

    fun insertNightmare(title: String, date: String, description: String, terrorRating: Int, tags: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("date", date)
            put("description", description)
            put("terrorRating", terrorRating)
            put("tags", tags)
        }
        return db.insert("NightmaresTable", null, values)
    }

    fun updateNightmare(id: Int, title: String, description: String, terrorRating: Int, tags: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("terrorRating", terrorRating)
            put("tags", tags)
        }

        db.update("NightmaresTable", values, "id = ?", arrayOf(id.toString()))
    }


    fun readNightmares(): List<MainActivity.NightmaresTable> {
        val db = readableDatabase
        val projection = arrayOf("id", "title", "date", "description", "terrorRating", "tags")
        val cursor = db.query(
            "NightmaresTable",
            projection,
            null,
            null,
            null,
            null,
            "terrorRating DESC" // Classifica por nível de terror
        )

        val nightmareList = mutableListOf<MainActivity.NightmaresTable>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val title = getString(getColumnIndexOrThrow("title"))
                val date = getString(getColumnIndexOrThrow("date"))
                val description = getString(getColumnIndexOrThrow("description"))
                val terrorRating = getInt(getColumnIndexOrThrow("terrorRating"))
                val tags = getString(getColumnIndexOrThrow("tags"))

                // Cria um objeto NightmaresTable e adiciona à lista
                val nightmare = MainActivity.NightmaresTable(id, title, date, description, terrorRating, tags)
                nightmareList.add(nightmare)
            }
        }
        cursor.close()

        return nightmareList
    }

}
