package com.example.checkgeneration

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.example.checkgeneration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        private const val DAY_CONST = 1
        private const val MONTH_CONST = 0
        private const val YEAR_CONST = 2000

        private const val DAY_KEY = "savedDayKey"
        private const val MONTH_KEY = "savedMonthKey"
        private const val YEAR_KEY = "savedYearKey"

        private const val FILE_NAME = "settings"
    }

    lateinit var binding : ActivityMainBinding
    private lateinit var prefs: SharedPreferences

    private var initialDay = DAY_CONST
    private var initialMonth = MONTH_CONST
    private var initialYear = YEAR_CONST

    override fun onPause() {
        super.onPause()
        val editor = prefs.edit()
        editor.putInt(DAY_KEY, initialDay).apply()
        editor.putInt(MONTH_KEY, initialMonth).apply()
        editor.putInt(YEAR_KEY, initialYear).apply()
    }

    override fun onResume() {
        super.onResume()
        initialDay = prefs.getInt(DAY_KEY, DAY_CONST)
        initialMonth = prefs.getInt(MONTH_KEY, MONTH_CONST)
        initialYear = prefs.getInt(YEAR_KEY, YEAR_CONST)
        checkGeneration(initialYear, initialMonth, initialDay)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun onClickCalendar (view: View) {
        DatePickerDialog(this,this, initialYear, initialMonth, initialDay).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        initialDay = dayOfMonth
        initialMonth = month
        initialYear = year
        checkGeneration(initialYear, initialMonth, initialDay)
    }

    private fun checkGeneration(yearOfBirth: Int, monthOfBirth : Int, dayOfBirth : Int) {

        when (yearOfBirth) {
            in 1946..1964 -> {
                binding.tvGenerationName.text = String.format(getString(R.string.boomer_gen), dayOfBirth, monthToText(monthOfBirth), yearOfBirth)
                binding.tvGenerationInfo.text = getString(R.string.boomer_info)
                binding.ivGenerationPicture.setImageResource(R.drawable.boomer_gen)
            }
            in 1965..1980 -> {
                binding.tvGenerationName.text = String.format(getString(R.string.x_gen), dayOfBirth, monthToText(monthOfBirth), yearOfBirth)
                binding.tvGenerationInfo.text = getString(R.string.x_info)
                binding.ivGenerationPicture.setImageResource(R.drawable.x_gen)
            }
            in 1981..1996 -> {
                binding.tvGenerationName.text = String.format(getString(R.string.y_gen), dayOfBirth, monthToText(monthOfBirth), yearOfBirth)
                binding.tvGenerationInfo.text = getString(R.string.y_info)
                binding.ivGenerationPicture.setImageResource(R.drawable.y_gen)
            }
            in 1997..2012 -> {
                binding.tvGenerationName.text = String.format(getString(R.string.z_gen), dayOfBirth, monthToText(monthOfBirth), yearOfBirth)
                binding.tvGenerationInfo.text = getString(R.string.z_info)
                binding.ivGenerationPicture.setImageResource(R.drawable.z_gen)
            }
            else -> {
                binding.tvGenerationName.text = String.format(getString(R.string.unknown_gen), dayOfBirth, monthToText(monthOfBirth), yearOfBirth)
                binding.tvGenerationInfo.text = ""
                binding.ivGenerationPicture.setImageResource(R.drawable.unknown_gen)
            }
        }
        binding.tvGenerationName.visibility = View.VISIBLE
        binding.ivGenerationPicture.visibility = View.VISIBLE
    }

    private fun monthToText (monthIndex : Int) : String {
        return when (monthIndex) {
            0 -> getString(R.string.jan)
            1 -> getString(R.string.feb)
            2 -> getString(R.string.mar)
            3 -> getString(R.string.apr)
            4 -> getString(R.string.may)
            5 -> getString(R.string.jun)
            6 -> getString(R.string.jul)
            7 -> getString(R.string.aug)
            8 -> getString(R.string.sep)
            9 -> getString(R.string.oct)
            10 -> getString(R.string.nov)
            11 -> getString(R.string.dec)
            else -> ""
        }
    }

}