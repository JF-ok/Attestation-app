package com.example.emoty

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FeedFragment : Fragment() {

    lateinit var noteRecyclerView: RecyclerView
    lateinit var addFloatingButton: FloatingActionButton
    lateinit var noteList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
//        val noteList: List<Note> = listOf(
//            Note("Заметка первая", "20:22"),
//            Note("Заметка вторая", "21:20"),
//            Note("Заметка третья", "22:30"),
//            Note("Заметка четвёртая", "00:20")
//

        noteRecyclerView = view.findViewById(R.id.note_recycler_view)
        addFloatingButton = view.findViewById(R.id.fab)
        noteRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        noteRecyclerView.adapter = NoteAdapter(noteList)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localTime = LocalTime.now()
        addFloatingButton.setOnClickListener {
            noteList.add(Note("Новая запись",localTime.format(DateTimeFormatter.ofPattern("HH:mm")).toString()))
            noteRecyclerView.adapter = NoteAdapter(noteList)
        }
    }

}