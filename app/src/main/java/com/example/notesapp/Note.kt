package com.example.notesapp

class Note(
    val id: Int,
    val title: String,
    val description: String,
    val dayOfWeek: Int,
    val priority: Int
) {
//    fun getDayAsString(position: Int): String? {
//        return when (position) {
//            1 -> "Понедельник"
//            2 -> "Вторник"
//            3 -> "Среда"
//            4 -> "Четверг"
//            5 -> "Пятинца"
//            6 -> "Суббота"
//            else -> "Воскресенье"
//        }
//    }

    fun getDayAsString(position: Int): String? {
        return when (position) {
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            else -> "Saturday"
        }
    }
}