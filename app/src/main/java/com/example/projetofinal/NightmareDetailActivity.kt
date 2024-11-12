package com.example.projetofinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast


class NightmareDetailActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var terrorRatingEditText: EditText
    private lateinit var tagsEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nightmare_detail)

        dbHelper = MyDatabaseHelper(this)

        val nightmareId = intent.getIntExtra("ID", -1)
        val title = intent.getStringExtra("TITLE") ?: ""
        val date = intent.getStringExtra("DATE") ?: ""
        val description = intent.getStringExtra("DESCRIPTION") ?: ""
        val terrorRating = intent.getIntExtra("TERROR_RATING", 0)
        val tags = intent.getStringExtra("TAGS") ?: ""

        titleEditText = findViewById(R.id.editTextTitle)
        descriptionEditText = findViewById(R.id.editTextDescription)
        terrorRatingEditText = findViewById(R.id.editTextTerrorRating)
        tagsEditText = findViewById(R.id.editTextTags)
        saveButton = findViewById(R.id.buttonSave)

        titleEditText.setText(title)
        descriptionEditText.setText(description)
        terrorRatingEditText.setText(terrorRating.toString())
        tagsEditText.setText(tags)

        saveButton.setOnClickListener {
            val updatedTitle = titleEditText.text.toString()
            val updatedDescription = descriptionEditText.text.toString()
            val updatedTerrorRating = terrorRatingEditText.text.toString().toIntOrNull() ?: terrorRating
            val updatedTags = tagsEditText.text.toString()

            dbHelper.updateNightmare(nightmareId, updatedTitle, description = updatedDescription, terrorRating = updatedTerrorRating, tags = updatedTags)

            Toast.makeText(this, "Pesadelo atualizado!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
