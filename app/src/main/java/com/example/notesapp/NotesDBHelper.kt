package com.example.notesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDBHelper(
    context: Context?
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "notes.db"
        private val DB_VERSION = 2
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(NotesContract.NotesEntry.CREATE_COMMAND)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(NotesContract.NotesEntry.DROP_COMMAND)
        onCreate(p0)
    }
}