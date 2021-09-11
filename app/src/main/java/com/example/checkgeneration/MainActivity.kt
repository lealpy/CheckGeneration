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

    lateinit var binding : ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    var initialDay = 1
    var initialMonth = 0
    var initialYear = 2000

    override fun onPause() {
        super.onPause()
        val editor = prefs.edit()
        editor.putInt("savedDay", initialDay).apply()
        editor.putInt("savedMonth", initialMonth).apply()
        editor.putInt("savedYear", initialYear).apply()
    }

    override fun onResume() {
        super.onResume()
        if (prefs.contains("savedDay") && prefs.contains("savedMonth") && prefs.contains("savedYear")) {
            initialDay = prefs.getInt("savedDay", 1)
            initialMonth = prefs.getInt("savedMonth", 0)
            initialYear = prefs.getInt("savedYear", 2000)
            checkGeneration(initialYear, initialMonth, initialDay)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    fun onClick (view: View) {
        DatePickerDialog(this,this, initialYear, initialMonth, initialDay).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        initialDay = dayOfMonth
        initialMonth = month
        initialYear = year
        checkGeneration(initialYear, initialMonth, initialDay)
    }

    fun checkGeneration(yearOfBirth: Int, monthofBirth : Int, dayOfBirth : Int) {

        when (yearOfBirth) {
            in 1946..1964 -> {
                binding.textView1.text = "Человек, рожденный $dayOfBirth ${monthToText(monthofBirth)} $yearOfBirth года, относится к поколению бэби-бумеров"
                //getString(R.string.boomer_gen)
                binding.textView2.text = getString(R.string.boomer_info)
                binding.imageView.setImageResource(R.drawable.boomer_gen)
            }
            in 1965..1980 -> {
                binding.textView1.text = "Человек, рожденный $dayOfBirth ${monthToText(monthofBirth)} $yearOfBirth года, относится к поколению X"
                //getString(R.string.x_gen)
                binding.textView2.text = getString(R.string.x_info)
                binding.imageView.setImageResource(R.drawable.x_gen)
            }
            in 1981..1996 -> {
                binding.textView1.text = "Человек, рожденный $dayOfBirth ${monthToText(monthofBirth)} $yearOfBirth года, относится к поколению Y (миллениалам)"
                //getString(R.string.y_gen)
                binding.textView2.text = getString(R.string.y_info)
                binding.imageView.setImageResource(R.drawable.y_gen)
            }
            in 1997..2012 -> {
                binding.textView1.text = "Человек, рожденный $dayOfBirth ${monthToText(monthofBirth)} $yearOfBirth года, относится к поколению Z (зумерам)"
                //getString(R.string.z_gen)
                binding.textView2.text = getString(R.string.z_info)
                binding.imageView.setImageResource(R.drawable.z_gen)
            }
            else -> {
                binding.textView1.text = "Человек, рожденный $dayOfBirth ${monthToText(monthofBirth)} $yearOfBirth года, не относится к поколениям бэби-бумеров, X, Y, Z"
                //getString(R.string.unknown_gen)
                binding.textView2.text = ""
                binding.imageView.setImageResource(R.drawable.unknown_gen)
            }
        }
        binding.textView1.visibility = View.VISIBLE
        binding.imageView.visibility = View.VISIBLE
    }

    fun monthToText (monthofBirth : Int) : String {
        return when (monthofBirth) {
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