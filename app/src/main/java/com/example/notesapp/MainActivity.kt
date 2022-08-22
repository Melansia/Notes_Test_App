package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    private var notes = ArrayList<Note>()
    private lateinit var adapter : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNotes = findViewById(R.id.rvNotes)

        notes.add(Note("Market","Buy stuff for lemonade","Sunday", 2))
        notes.add(Note("Dentist","Call Vasilis to close a visit","Monday", 3))
        notes.add(Note("Skroutz","Pick up Moms Tablet","Tuesday", 2))
        notes.add(Note("Sea","Trip to Voliagmenis Lake","Wednesday", 3))
        notes.add(Note("Syntagma","Check for Games","Wednesday", 1))
        notes.add(Note("Market","Buy Coffee","Thursday", 2))
        notes.add(Note("Monastiraki","Meet with Petir","Friday", 1))

        adapter = NotesAdapter(notes)
        rvNotes.layoutManager = GridLayoutManager(this, 3)
        rvNotes.adapter = adapter
    }
}