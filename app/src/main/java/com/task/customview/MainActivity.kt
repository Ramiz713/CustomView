package com.task.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeButton.setOnClickListener {
            val randomCount = (3..20).random()
            val randomMax = (2..1200).random()
            val randomInts = (1..randomCount).map { (1..randomMax).random() }
            histogramView.setDataSource(randomInts)
        }
    }
}
