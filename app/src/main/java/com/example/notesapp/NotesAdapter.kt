package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter(private val notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotesViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item, viewGroup, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(notesViewHolder: NotesViewHolder, i: Int) {
        val note = notes[i]
        notesViewHolder.tvTitle.text = note.title
        notesViewHolder.tvDescription.text = note.description
        notesViewHolder.tvDayOfWeek.text = note.dayOfWeek
        var colorId = 0
        val priority = note.priority
        when(priority) {
            1 -> colorId = notesViewHolder.itemView.resources.getColor(android.R.color.holo_red_light)
            2 -> colorId = notesViewHolder.itemView.resources.getColor(android.R.color.holo_orange_light)
            3 -> colorId = notesViewHolder.itemView.resources.getColor(android.R.color.holo_green_light)
        }
        notesViewHolder.tvTitle.setBackgroundColor(colorId)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NotesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvDescription: TextView
        val tvDayOfWeek: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvDayOfWeek = itemView.findViewById(R.id.tvDayOfWeek)
        }
    }
}
