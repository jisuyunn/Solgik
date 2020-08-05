package com.example.solgikb.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun calculatedDay(day: String?): String {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val format = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val dformat = LocalDate.parse(day, format)
        val dcal = Calendar.getInstance()
        dcal.set(dformat.year, dformat.monthValue - 1, dformat.dayOfMonth)
        dcal.time
        val dday = dcal.timeInMillis / ONE_DAY
        val today = Calendar.getInstance().timeInMillis / ONE_DAY

        var result = dday - today
        val strFormat: String
        if(result > 0) strFormat = "D-%d"
        else if(result == 0L) strFormat = "D-Day"
        else {
            result *= -1
            strFormat = "D+%d"
        }
        return String.format(strFormat, result)
    } else {
        return "낮은버전"
    }

}

