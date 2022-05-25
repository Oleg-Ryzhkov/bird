package com.example.game4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoadScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_screen)

        val load = findViewById<TextView>(R.id.textView2)

        load.animate().rotationX(90f).setDuration(5000)

        
    }
}