package com.example.notesapp

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class AddNoteActivity : AppCompatActivity() {

    private lateinit var edTitle: EditText
    private lateinit var edDescription: EditText
    private lateinit var spinnerDaysOfWeek: Spinner
    private lateinit var rgPriority: RadioGroup
    private lateinit var btnSaveNote: Button

    private lateinit var dbHelper: NotesDBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        this@AddNoteActivity.supportActionBar?.hide()

        dbHelper = NotesDBHelper(this)
        database = dbHelper.writableDatabase

        edTitle = findViewById(R.id.edTitle)
        edDescription = findViewById(R.id.edDescription)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        rgPriority = findViewById(R.id.rgPriority)
        btnSaveNote = findViewById(R.id.btnSaveNote)


        btnSaveNote.setOnClickListener {
            val title = edTitle.text.toString().trim()
            val description = edDescription.text.toString().trim()
            val dayOfWeek = spinnerDaysOfWeek.selectedItemPosition
            val radioButtonID = findViewById<RadioButton>(rgPriority.checkedRadioButtonId)
            val priority = radioButtonID.text.toString().toInt()
            if (isField(title, description)) {
                val contentValues = ContentValues()
                contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, title)
                contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, description)
                contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, dayOfWeek + 1)
                contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority)
                database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.warning_fill_filds), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isField(title: String, description: String): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }
}