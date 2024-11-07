package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        val buttonStartDiary = findViewById<Button>(R.id.buttonStartDiary)
        buttonStartDiary.setOnClickListener {
            // Abre a MainActivity quando o botão é clicado
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Fecha a IntroActivity para que não volte ao pressionar "back"
        }
    }
}