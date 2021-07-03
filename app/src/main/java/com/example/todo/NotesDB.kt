package com.example.todo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class NotesDB(context: Context) :
    SQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {

    companion object {
        private const val DATA_BASE_VERSION = 1
        private const val DATA_BASE_NAME = "NotesDataBase"
        private const val TABLE_NAME = "NotesTable"

        private const val KEY_ID = "_id"
        private const val COLUMN_TITLE = "NoteTitle"
        private const val COLUMN_DATE = "NoteDate"
        private const val COLUMN_DESCRIPTION = "NoteDescription"
        private const val COLUMN_COLOR = "NoteColor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOTES_TABLE =
            ("CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_TITLE + " TEXT," + COLUMN_DATE + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_COLOR + " INTEGER" + ")")
        db?.execSQL(CREATE_NOTES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun View_Notes(): ArrayList<Note> {
        var mylist: ArrayList<Note> = ArrayList()
        val myQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(myQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(myQuery)
            return mylist
        }

        var Id: Int
        var title: String
        var description: String
        var date: String
        var color: Int

        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
                description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                color = cursor.getInt(cursor.getColumnIndex(COLUMN_COLOR))
                val myNote = Note(Id, title, date, description, color)
                mylist.add(myNote)
            } while (cursor.moveToNext())
        }
        return mylist

    }

    fun Insert_Note(note: Note): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, note.title)
        contentValues.put(COLUMN_DATE, note.date)
        contentValues.put(COLUMN_DESCRIPTION, note.description)
        contentValues.put(COLUMN_COLOR, note.color)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }

    fun Update_Note(note: Note): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, note.title)
        contentValues.put(COLUMN_DATE, note.date)
        contentValues.put(COLUMN_DESCRIPTION, note.description)
        contentValues.put(COLUMN_COLOR, note.color)
        val success = db.update(TABLE_NAME, contentValues, KEY_ID + "=" + note.id, null)
        db.close()
        return success
    }

    fun Delete_Note(note: Note): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME,KEY_ID + "=" + note.id,null)
        db.close()
        return success
    }


}