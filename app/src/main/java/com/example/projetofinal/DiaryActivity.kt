package com.example.projetofinal

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent


class DiaryActivity : AppCompatActivity() {
    private lateinit var recyclerViewNightmare: RecyclerView
    private lateinit var nightmareAdapter: NightmareAdapter
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        recyclerViewNightmare = findViewById(R.id.recyclerViewNightmare)
        dbHelper = MyDatabaseHelper(this)

        val nightmares = dbHelper.readNightmares()

        recyclerViewNightmare.layoutManager = LinearLayoutManager(this)
        nightmareAdapter = NightmareAdapter(nightmares) { nightmare ->
            val intent = Intent(this, NightmareDetailActivity::class.java).apply {
                putExtra("id", nightmare.id)
                putExtra("title", nightmare.title)
                putExtra("description", nightmare.description)
                putExtra("terrorRating", nightmare.terrorRating)
                putExtra("tags", nightmare.tags)
            }
            startActivity(intent)
        }

        recyclerViewNightmare.adapter = nightmareAdapter
    }
}