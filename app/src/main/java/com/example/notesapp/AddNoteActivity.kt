package com.example.notesapp

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        edTitle = findViewById(R.id.edTitle)
        edDescription = findViewById(R.id.edDescription)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        rgPriority = findViewById(R.id.rgPriority)
        btnSaveNote = findViewById(R.id.btnSaveNote)

        btnSaveNote.setOnClickListener {
            val title = edTitle.text.toString().trim()
            val description = edDescription.text.toString().trim()
            val dayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
            val radioButton = findViewById<RadioButton>(rgPriority.checkedRadioButtonId)
            val priority = radioButton.text.toString().toInt()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EXTRA_TITLE", title)
            intent.putExtra("EXTRA_DESCRIPTION", description)
            intent.putExtra("EXTRA_WEEKDAY", dayOfWeek)
            intent.putExtra("EXTRA_PRIORITY", priority)
            startActivity(intent)
        }
    }


//    fun onClickSaveNote(view: View?) {
//        val title = edTitle.text.toString().trim()
//        val description = edDescription.text.toString().trim()
//        val dayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
//        val radioButton = findViewById<RadioButton>(rgPriority.checkedRadioButtonId)
//        val priority = radioButton.text.toString().toInt()
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("EXTRA_TITLE", title)
//        intent.putExtra("EXTRA_DESCRIPTION", description)
//        intent.putExtra("EXTRA_WEEKDAY", dayOfWeek)
//        intent.putExtra("EXTRA_PRIORITY", priority)
//        startActivity(intent)
//    }
}