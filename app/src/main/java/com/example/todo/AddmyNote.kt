package com.example.todo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.todo.databinding.ActivityAddmyNoteBinding
import java.util.*

class AddmyNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddmyNoteBinding
    private var mynote = Note(0, "", "", "", null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddmyNoteBinding.inflate(layoutInflater)

        var selectedcolorid: View?
        var selectednotecolor: Int?
        selectednotecolor = null
        selectedcolorid = null

        setContentView(binding.root)
        binding.notecolor1.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#FFFF00")
            selectedcolorid = binding.notecolor1
        }
        binding.notecolor2.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#E30B5C")
            selectedcolorid = binding.notecolor2
        }
        binding.notecolor3.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#FF5F1F")
            selectedcolorid = binding.notecolor3
        }
        binding.notecolor4.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor4.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#00FFFF")
            selectedcolorid = binding.notecolor4
        }
        binding.notecolor5.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor5.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#00FF00")
            selectedcolorid = binding.notecolor5
        }
        binding.notecolor6.setOnClickListener {
            closeKeyboard()
            binding.titleofnote.clearFocus()
            binding.dateofnote.clearFocus()
            binding.descriptionofnote.clearFocus()
            selectedcolorid?.setBackgroundResource(R.drawable.notecolorholder)
            binding.notecolor6.setBackgroundColor(Color.parseColor("#FFFFFF"))
            selectednotecolor = Color.parseColor("#8F00FF")
            selectedcolorid = binding.notecolor6
        }
        binding.addmynote.setOnClickListener {
            closeKeyboard()
            mynote.title = binding.notetitle.text.toString()
            mynote.date = binding.notedate.text.toString()
            mynote.description = binding.notedescription.text.toString()
            mynote.color = selectednotecolor

            if (mynote.title.isNotEmpty() && mynote.date.isNotEmpty() && mynote.description.isNotEmpty() && selectednotecolor != null) {
                val db = NotesDB(this)
                db.Insert_Note(mynote)
                val intent = Intent(this@AddmyNote, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@AddmyNote, "Please enter all the feilds", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hidetheKeyboard =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hidetheKeyboard.hideSoftInputFromWindow(view.windowToken, 0)

        }
    }

}
