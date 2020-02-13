package com.example.lecture03

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private var x: Double = 0.0
    private var y: Double = 0.0
    private var lineSeries: LineGraphSeries<DataPoint>? = null

    lateinit var displayUserNamePassed : String
    var displayUserPassedRandomNum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //display user name from previous activity
        displayUserNamePassed= intent.getStringExtra("user_name_passed").toString()
        display_user_name.text = displayUserNamePassed

        //display random number generated from previous activity
        displayUserPassedRandomNum = intent.getIntExtra("random_key",0)
        display_random_number.text = displayUserPassedRandomNum.toString()

        displayGraphSeries()
    }

    fun displayGraphSeries() {
        lineSeries = LineGraphSeries()

        for (i in 0..100) {
            x = x + 0.1
            y = Math.cos(x)
            lineSeries?.appendData(DataPoint(x, y), true, 100)
        }
        graph_to_display.addSeries(lineSeries)
    }


    fun back_button_pressed(view: View){
        val getBackIntent = Intent(this, MainActivity::class.java)
        getBackIntent.putExtra("received_random_num", displayUserPassedRandomNum)
        setResult(Activity.RESULT_OK, getBackIntent)
        finish()
    }
}
