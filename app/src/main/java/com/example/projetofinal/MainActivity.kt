package com.example.projetofinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDatabaseHelper(this)

        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val editTextTerrorRating = findViewById<EditText>(R.id.editTextTerrorRating)
        val editTextTags = findViewById<EditText>(R.id.editTextTags)
        val buttonInsertNightmare = findViewById<Button>(R.id.buttonInsertNightmare)
        val buttonViewNightmares = findViewById<Button>(R.id.buttonViewNightmares)
        val textViewNightmareData = findViewById<TextView>(R.id.textViewNightmareData)

        buttonInsertNightmare.setOnClickListener {
            val title = editTextTitle.text.toString()
            val date = editTextDate.text.toString()
            val description = editTextDescription.text.toString()
            val terrorRating = editTextTerrorRating.text.toString().toIntOrNull()
            val tags = editTextTags.text.toString()

            if (title.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty() && terrorRating != null) {
                dbHelper.insertNightmare(title, date, description, terrorRating, tags)
                editTextTitle.text.clear()
                editTextDate.text.clear()
                editTextDescription.text.clear()
                editTextTerrorRating.text.clear()
                editTextTags.text.clear()
            }
        }

        buttonViewNightmares.setOnClickListener {
            val nightmares = dbHelper.readNightmares()
            textViewNightmareData.text = nightmares.joinToString("\n\n")
        }
    }
}
