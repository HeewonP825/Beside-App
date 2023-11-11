package com.beside.hackathon.core.utils

import java.text.SimpleDateFormat
import java.util.Date

object DataUtils{
    fun conventDateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }
}