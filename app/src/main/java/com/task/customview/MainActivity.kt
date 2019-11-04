package com.task.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.customview.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListFragment.create())
            .addToBackStack("listFragment")
            .commit()
    }
}
