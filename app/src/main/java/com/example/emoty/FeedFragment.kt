package com.example.emoty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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
    lateinit var localTime: LocalTime

    lateinit var firstFab: FloatingActionButton
    lateinit var secondFab: FloatingActionButton
    lateinit var thirdFab: FloatingActionButton
    lateinit var buttonTag: String
    var isFABOpen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        firstFab = view.findViewById(R.id.first_fab)
        secondFab = view.findViewById(R.id.second_fab)
        thirdFab = view.findViewById(R.id.third_fab)

        noteList = mutableListOf(
            Note("Первая тестовая заметка", "00:00", "neutral"),
            Note("Вторая тестовая заметка", "01:01", "neutral"),
            Note("Третья тестовая заметка", "02:10", "neutral"),
        )
        noteRecyclerView = view.findViewById(R.id.note_recycler_view)
        addFloatingButton = view.findViewById(R.id.fab)
        editText = view.findViewById(R.id.addNoteEditText)
        noteRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        noteRecyclerView.adapter = NoteAdapter(noteList)
        return view
    }

    private fun showFABMenu() {
        isFABOpen = true
        firstFab.animate().translationY((-firstFab.height - firstFab.height * 0.2F).toFloat())
        secondFab.animate().translationY((-secondFab.height - firstFab.height * 0.2F).toFloat() * 2)
        thirdFab.animate().translationY((-thirdFab.height - firstFab.height * 0.2F).toFloat() * 3)
    }

    private fun closeFABMenu() {
        isFABOpen = false
        firstFab.animate().translationY(0F)
        secondFab.animate().translationY(0F)
        thirdFab.animate().translationY(0F)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.text.isNotEmpty()) {
                    addFloatingButton.setImageResource(R.drawable.ic_apply_icon_white)
                    addFloatingButton.tag = "Ready"
                } else {
                    addFloatingButton.setImageResource(R.drawable.ic_add_icon_white)
                    addFloatingButton.tag = "NotReady"
                    closeFABMenu()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        addFloatingButton.setOnLongClickListener {
            if (!isFABOpen) {
                showFABMenu()
            } else {
                closeFABMenu()
            }
            buttonTag = "Neutral"
            true

        }

        firstFab.setOnClickListener {
            buttonTag = "Good"
            closeFABMenu()
            Toast.makeText(requireContext(), buttonTag, Toast.LENGTH_SHORT).show()
        }
        secondFab.setOnClickListener {
            buttonTag = "Amazing"
            closeFABMenu()
            Toast.makeText(requireContext(), buttonTag, Toast.LENGTH_SHORT).show()
        }
        thirdFab.setOnClickListener {
            buttonTag = "Bad"
            closeFABMenu()
            Toast.makeText(requireContext(), buttonTag, Toast.LENGTH_SHORT).show()
        }

        addFloatingButton.setOnClickListener {
            if (addFloatingButton.tag == "Ready") {
                localTime = java.time.LocalTime.now()
                buttonTag = "Neutral"
                showFABMenu()
                noteList.add(
                    Note(
                        editText.text.toString(),
                        localTime.format(DateTimeFormatter.ofPattern("HH:mm")).toString(),
                        buttonTag

                    )
                )
                editText.text = null
            } else {
                Toast.makeText(context, "Напечатайте что-то...", Toast.LENGTH_SHORT).show()
            }
            noteRecyclerView.adapter = NoteAdapter(noteList)
        }
    }
}