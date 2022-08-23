package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class MainActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    val notes: ArrayList<Note> = ArrayList()
    private lateinit var adapter: NotesAdapter


    private lateinit var title: String
    private lateinit var description: String
    private lateinit var dayOfWeek: String
    private var priority: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNotes = findViewById(R.id.rvNotes)

        if (notes.isEmpty()) {
            notes.add(Note("Market", "Buy stuff for lemonade", "Sunday", 2))
            notes.add(Note("Dentist", "Call Vasilis to close a visit", "Monday", 3))
            notes.add(Note("Skroutz", "Pick up Moms Tablet", "Tuesday", 2))
            notes.add(Note("Sea", "Trip to Voliagmenis Lake", "Wednesday", 3))
            notes.add(Note("Market", "Buy Coffee", "Thursday", 2))
            notes.add(Note("Monastiraki", "Meet with Petir", "Friday", 1))
            notes.add(Note("Syntagma", "Check for Games", "Saturday", 1))
        }

        adapter = NotesAdapter(notes)
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter

        if (intent != null && intent.hasExtra("EXTRA_PRIORITY")) {
            title = intent.getStringExtra("EXTRA_TITLE").toString()
            description = intent.getStringExtra("EXTRA_DESCRIPTION").toString()
            dayOfWeek = intent.getStringExtra("EXTRA_WEEKDAY").toString()
            priority = intent.getIntExtra("EXTRA_PRIORITY", 1)
            val note = Note(title, description, dayOfWeek, priority)
            notes.add(note)
        }

    }

    fun onClickAddNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }
}