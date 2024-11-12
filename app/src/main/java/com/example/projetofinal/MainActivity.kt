package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDatabaseHelper(this)

        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val spinnerTerrorRating = findViewById<Spinner>(R.id.spinnerTerrorRating)
        val editTextTags = findViewById<EditText>(R.id.editTextTags)
        val buttonInsertNightmare = findViewById<Button>(R.id.buttonInsertNightmare)
        val buttonViewNightmares = findViewById<Button>(R.id.buttonViewNightmares)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.format(calendar.time)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.terror_rating_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTerrorRating.adapter = adapter

        editTextDate.setText(currentDate)

        buttonInsertNightmare.setOnClickListener {
            val title = editTextTitle.text.toString()
            val date = editTextDate.text.toString()
            val description = editTextDescription.text.toString()
            val terrorRating = spinnerTerrorRating.selectedItem.toString().toIntOrNull()
            val tags = editTextTags.text.toString()

            if (title.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty() && terrorRating != null) {
                dbHelper.insertNightmare(title, date, description, terrorRating, tags)
                editTextTitle.text.clear()
                editTextDescription.text.clear()
                editTextTags.text.clear()
                spinnerTerrorRating.setSelection(0)
            }

        }

        buttonViewNightmares.setOnClickListener {

            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }
    }
    data class NightmaresTable(
        val id: Int,
        val title: String,
        val date: String,
        val description: String,
        val terrorRating: Int,
        val tags: String
    )
}
