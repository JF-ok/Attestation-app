package com.example.emoty

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emoty.R

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val noteTextView: TextView = itemView.findViewById(R.id.note_text_view)
    private val timeTextView: TextView = itemView.findViewById(R.id.time_text_view)

    fun bind(note: Note) {
        noteTextView.text = note.note
        timeTextView.text = note.time //"Строка - ${note.time}"
    }
}