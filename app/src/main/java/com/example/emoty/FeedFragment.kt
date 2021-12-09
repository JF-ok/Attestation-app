package com.example.emoty

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FeedFragment : Fragment() {

    lateinit var noteRecyclerView: RecyclerView
    lateinit var addFloatingButton: FloatingActionButton
    lateinit var editText: EditText
    lateinit var noteList: MutableList<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        noteList = mutableListOf(
            Note("Первая тестовая заметка", "00:00"),
            Note("Вторая тестовая заметка", "01:01"),
            Note("Третья тестовая заметка", "02:10"),
        )
        noteRecyclerView = view.findViewById(R.id.note_recycler_view)
        addFloatingButton = view.findViewById(R.id.fab)
        editText = view.findViewById(R.id.addNoteEditText)
        noteRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        noteRecyclerView.adapter = NoteAdapter(noteList)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localTime = LocalTime.now() //%%


        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.text.isNotEmpty()){
                    addFloatingButton.setImageResource(R.drawable.ic_apply_icon_white)
                    addFloatingButton.tag = "Ready"
                } else
                {
                    addFloatingButton.setImageResource(R.drawable.ic_add_icon_white)
                    addFloatingButton.tag = "NotReady"
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        addFloatingButton.setOnClickListener {
            if (addFloatingButton.tag == "Ready"){
                noteList.add(Note(editText.text.toString(),localTime.format(DateTimeFormatter.ofPattern("HH:mm")).toString()))
                editText.text = null
            }
            else{
                Toast.makeText(context, "Напечатайте что-то...", Toast.LENGTH_SHORT).show()
            }
            noteRecyclerView.adapter = NoteAdapter(noteList)
        }
    }

}