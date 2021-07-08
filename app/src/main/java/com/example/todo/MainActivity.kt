package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todo.databinding.ActivityMainBinding
import java.util.*

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
        binding.mynotesrv.layoutManager = LinearLayoutManager(this)
        db = NotesDB(this)
        myList = db.View_Notes()
        if (myList.size > 0) {
            setupmyrv(myList)
        }
        val dt = this

        binding.searchthenote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val updlist: MutableList<Note> = mutableListOf()
                val str = newText!!.toLowerCase(Locale.getDefault())
                val m = str.length
                val currlist = db.View_Notes()
                if (m > 0) {
                    currlist.forEach {
                        val len = it.title.length
                        if (len >= m) {
                            val str1 = it.title.toLowerCase(Locale.getDefault())
                            if (str1.substring(0, m) == str) {
                                updlist.add(it)
                            }
                        }
                    }
                    setupmyrv(updlist)
                } else {
                    setupmyrv(currlist)
                }
                return false
            }

        })

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