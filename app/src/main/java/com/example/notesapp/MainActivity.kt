package com.example.notesapp

import android.annotation.SuppressLint
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
    private lateinit var database: SQLiteDatabase


    private lateinit var title: String
    private lateinit var description: String
    private lateinit var dayOfWeek: String
    private var priority: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this@MainActivity.supportActionBar?.hide()

        rvNotes = findViewById(R.id.rvNotes)
        dbHelper = NotesDBHelper(this)
        database = dbHelper.writableDatabase
        getData()

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
        val id = notes[position].id
        val where = NotesContract.NotesEntry._ID + " = ?"
        val whereArgs = arrayOf(id.toString())
        database.delete(NotesContract.NotesEntry.TABLE_NAME, where, whereArgs)
        getData()
        adapter.notifyDataSetChanged()
    }

    fun onClickAddNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("Range")
    private fun getData() {
        notes.clear()
        val cursor =
            database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry._ID))
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION))
            val dayOfWeek = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK))
            val priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY))
            val note = Note(id, title, description,dayOfWeek,priority)
            notes.add(note)
        }
        cursor.close()
    }
}