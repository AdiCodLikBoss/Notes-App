package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.databinding.ActivityUpdatemyNoteBinding

class UpdateMyNote : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatemyNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatemyNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title1")
        val date = intent.getStringExtra("date1")
        val description = intent.getStringExtra("description1")
        val id = intent.getIntExtra("id1",0)
        val color = intent.getIntExtra("color1",0)
        binding.updatednotetitle.setText(title)
        binding.updatednotedate.setText(date)
        binding.updatednotedescription.setText(description)
        binding.updatemynote.setOnClickListener{
            val updatedtitle = binding.updatednotetitle.text.toString()
            val updateddate = binding.updatednotedate.text.toString()
            val updateddescription = binding.updatednotedescription.text.toString()
            val note = Note(id,updatedtitle,updateddate,updateddescription,color)
            val db = NotesDB(this)
            db.Update_Note(note)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}


