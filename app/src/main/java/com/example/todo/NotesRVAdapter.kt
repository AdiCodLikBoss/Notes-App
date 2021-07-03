package com.example.todo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(
    private val myNoteList: MutableList<Note>,
    private val listener: onmyItemClickListener
) :
    RecyclerView.Adapter<NotesRVAdapter.myviewholder>() {

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val titleofnote = itemView.findViewById<TextView>(R.id.titleofmynote)
        val dateofnote = itemView.findViewById<TextView>(R.id.dateofmynote)
        val descriptionofnote = itemView.findViewById<TextView>(R.id.descriptionofmynote)
        val colorofNote = itemView.findViewById<LinearLayout>(R.id.llnote)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(itemView, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_note_item, parent, false)
        return myviewholder(item)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currNote = myNoteList[position]
        holder.titleofnote.text = currNote.title
        holder.dateofnote.text = currNote.date
        holder.descriptionofnote.text = currNote.description
        holder.colorofNote.setBackgroundColor(currNote.color!!)
    }

    override fun getItemCount(): Int {
        return myNoteList.size
    }

    interface onmyItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}