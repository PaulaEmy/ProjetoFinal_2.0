package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout

class FirstPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        val layout = findViewById<LinearLayout>(R.id.root_layout) // Defina o ID do layout raiz no XML

        layout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            } else {
                false
            }
        }
    }
}
