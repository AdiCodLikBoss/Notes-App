package com.example.todo


data class Note(
    var id:Int,
    var title: String,
    var date: String,
    var description: String,
    var color: Int?
)
