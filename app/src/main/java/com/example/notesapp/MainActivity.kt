package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    val notes = ArrayList<Note>()
    private lateinit var adapter : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNotes = findViewById(R.id.rvNotes)

        notes.add(Note("Market","Buy stuff for lemonade","Sunday", 2))
        notes.add(Note("Dentist","Call Vasilis to close a visit","Monday", 3))
        notes.add(Note("Skroutz","Pick up Moms Tablet","Tuesday", 2))
        notes.add(Note("Sea","Trip to Voliagmenis Lake","Wednesday", 3))
        notes.add(Note("Market","Buy Coffee","Thursday", 2))
        notes.add(Note("Monastiraki","Meet with Petir","Friday", 1))
        notes.add(Note("Syntagma","Check for Games","Saturday", 1))

        adapter = NotesAdapter(notes)
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter
    }

    fun onClickAddNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }
}