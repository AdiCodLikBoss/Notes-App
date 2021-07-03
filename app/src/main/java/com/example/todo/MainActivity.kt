package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NotesRVAdapter.onmyItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotesDB
    private lateinit var myList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addanote.setOnClickListener {
            val intent = Intent(this, AddmyNote::class.java)
            startActivity(intent)
        }
        binding.mynotesrv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        db = NotesDB(this)
        myList = db.View_Notes()
        if (myList.size > 0) {
            setupmyrv(myList)
        }
    }

    fun setupmyrv(myList: MutableList<Note>) {
        val myRVAdapter = NotesRVAdapter(myList, this)
        binding.mynotesrv.adapter = myRVAdapter
    }

    override fun onItemClick(view: View, position: Int) {

        val updatebtn = view.findViewById<ImageView>(R.id.updatethenote)
        val deletebtn = view.findViewById<ImageView>(R.id.deletethenote)
        val curr_Item = myList[position]
        updatebtn.setOnClickListener {
            val intent = Intent(this, UpdateMyNote::class.java)
            intent.putExtra("title1", curr_Item.title)
            intent.putExtra("date1", curr_Item.date)
            intent.putExtra("description1", curr_Item.description)
            intent.putExtra("id1", curr_Item.id)
            intent.putExtra("color1", curr_Item.color)
            startActivity(intent)
        }
        deletebtn.setOnClickListener {
            var success = db.Delete_Note(curr_Item)
            if (success != -1) {
                myList = db.View_Notes()
                setupmyrv(myList)
            }
        }
    }
}