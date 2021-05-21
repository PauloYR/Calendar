package com.paulo.calendar

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*


class MCalendar(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    private var param: ViewGroup.LayoutParams? = null
    private var linearLayout1: LinearLayout? = null
    private var linearLayout2: LinearLayout? = null
    private var linearLayout3: LinearLayout? = null
    private var linearLayout4: LinearLayout? = null
    private var linearLayout5: LinearLayout? = null
    private var linearLayout6: LinearLayout? = null
    private var linearLayout7: LinearLayout? = null

    init {
        initParams()
        initLiDates(context)
        addNamesDate()
        addDays()
    }

    private fun initParams() {
        param =  ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initLiDates(context: Context?) {
        val params =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(14, 0, 0, 0)

        linearLayout1 = LinearLayout(context)
        linearLayout1?.gravity = Gravity.CENTER
        linearLayout1?.orientation = VERTICAL

        linearLayout2 = LinearLayout(context)
        linearLayout2?.orientation = VERTICAL
        linearLayout2?.gravity =  Gravity.CENTER
        linearLayout2?.layoutParams = params

        linearLayout3 = LinearLayout(context)
        linearLayout3?.orientation = VERTICAL
        linearLayout3?.gravity =  Gravity.CENTER
        linearLayout3?.layoutParams = params

        linearLayout4 = LinearLayout(context)
        linearLayout4?.orientation = VERTICAL
        linearLayout4?.gravity = Gravity.CENTER
        linearLayout4?.layoutParams = params

        linearLayout5 = LinearLayout(context)
        linearLayout5?.orientation = VERTICAL
        linearLayout5?.gravity = Gravity.CENTER
        linearLayout5?.layoutParams = params

        linearLayout6 = LinearLayout(context)
        linearLayout6?.orientation = VERTICAL
        linearLayout6?.gravity =  Gravity.CENTER
        linearLayout6?.layoutParams = params


        linearLayout7 = LinearLayout(context)
        linearLayout7?.orientation = VERTICAL
        linearLayout7?.layoutParams = params
        linearLayout7?.gravity =  Gravity.CENTER
    }


    private fun addDays() {
        this.orientation = VERTICAL

        var calendar = Calendar.getInstance()

        val daysMonth =
            getNumberDayInMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val firstDay = SimpleDateFormat("EEEE", getCurrentLocale(context)).format(calendar.time)

        val daysBackMonth = getNumberBackMonth(firstDay)

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))

        val lastDay = SimpleDateFormat("EEEE", getCurrentLocale(context)).format(calendar.time)

        val daysNextMonth = getNumberBackMonth(lastDay) + 6

        var calendarBack = Calendar.getInstance(getCurrentLocale(context))


        var backMonth = calendarBack.get(Calendar.MONTH) - 1

        if (backMonth <= 0) {
            backMonth = 11
        }

        calendarBack.set(Calendar.MONTH, backMonth)

        var lastDayBackMonth =
            calendarBack.getActualMaximum(Calendar.DAY_OF_MONTH) + 1 + daysBackMonth

        var count = 1

        for (i in -1 downTo daysBackMonth) {
            val textView = TextView(context)
            textView.text = "$lastDayBackMonth"
            count = addText(count, textView)
            lastDayBackMonth++
            count++
        }

        for (i in 1..daysMonth) {
            val textView = TextView(context)
            textView.text = "$i"
            count = addText(count, textView)
            count++
        }

        for (i in 1..daysNextMonth) {
            val textView = TextView(context)
            textView.text = "$i"
            count = addText(count, textView)
            count++
        }

        val liDates = LinearLayout(context)

        liDates.addView(linearLayout1)
        liDates.addView(linearLayout2)
        liDates.addView(linearLayout3)
        liDates.addView(linearLayout4)
        liDates.addView(linearLayout5)
        liDates.addView(linearLayout6)
        liDates.addView(linearLayout7)

        addView(liDates)
    }

    private fun addNamesDate() {
        val tvSunday = TextView(context)
        tvSunday.layoutParams = param
        tvSunday.text = "S"
        val tvMonday = TextView(context)
        tvMonday.text = "M"
        tvMonday.layoutParams = param
        val tvTuesday = TextView(context)
        tvTuesday.text = "T"
        tvTuesday.layoutParams = param
        val tvWednesday = TextView(context)
        tvWednesday.text = "W"
        tvWednesday.layoutParams = param
        val tvThursday = TextView(context)
        tvThursday.text = "T"
        tvThursday.layoutParams = param
        val tvFriday = TextView(context)
        tvFriday.text = "F"
        tvFriday.layoutParams = param
        val tvSaturday = TextView(context)
        tvSaturday.text = "S"
        tvSaturday.layoutParams = param

        val linearDates = LinearLayout(context)

        linearLayout1?.addView(tvSunday)
        linearLayout2?.addView(tvMonday)
        linearLayout3?.addView(tvTuesday)
        linearLayout4?.addView(tvWednesday)
        linearLayout5?.addView(tvThursday)
        linearLayout6?.addView(tvFriday)
        linearLayout7?.addView(tvSaturday)
        addView(linearDates)
    }

    private fun addText(count: Int, textView: TextView): Int {
        textView.layoutParams = param
        when (count) {
            1 -> {
                linearLayout1?.addView(textView)
            }
            2 -> {
                linearLayout2?.addView(textView)
            }
            3 -> {
                linearLayout3?.addView(textView)
            }
            4 -> {
                linearLayout4?.addView(textView)
            }
            5 -> {
                linearLayout5?.addView(textView)
            }
            6 -> {
                linearLayout6?.addView(textView)
            }
            7 -> {
                linearLayout7?.addView(textView)
                return 0
            }
        }
        return count
    }

    private fun getCurrentLocale(context: Context): Locale? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0)
        } else {
            context.resources.configuration.locale
        }
    }

    private fun getNumberDayInMonth(year: Int, month: Int): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            YearMonth.of(year, month).lengthOfMonth()
        } else {
            val cal: Calendar = GregorianCalendar(year, month, 1)
            cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

    private fun getNumberBackMonth(day: String): Int {
        when (day) {
            "Sunday" -> return 0
            "Monday" -> return -1
            "Tuesday" -> return -2
            "Wednesday" -> return -3
            "Thursday" -> return -4
            "Friday" -> return -5
            "Saturday" -> return -6
        }
        throw NullPointerException()
    }


}