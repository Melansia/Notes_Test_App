package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class MainActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    private val notes: ArrayList<Note> = ArrayList()
    private lateinit var adapter: NotesAdapter
    private lateinit var dbHelper: NotesDBHelper


    private lateinit var title: String
    private lateinit var description: String
    private lateinit var dayOfWeek: String
    private var priority: Int = 3

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotes = findViewById(R.id.rvNotes)
        dbHelper = NotesDBHelper(this)
        val database: SQLiteDatabase = dbHelper.writableDatabase
//        database.delete(NotesContract.NotesEntry.TABLE_NAME, null, null)

        val cursor =
            database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION))
            val dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK))
            val priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY))
            val note = Note(title, description,dayOfWeek,priority)
            notes.add(note)
        }
        cursor.close()

        adapter = NotesAdapter(notes)
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter


        adapter.setOnNoteClickListener(object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(position: Int) {
                Toast.makeText(this@MainActivity, "Position nr: $position", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onLongClick(position: Int) {
                Toast.makeText(this@MainActivity, "Deleting item: $position", Toast.LENGTH_SHORT)
                    .show()
                remove(position)
            }
        })
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                remove(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(rvNotes)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun remove(position: Int) {
        notes.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    fun onClickAddNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }
}