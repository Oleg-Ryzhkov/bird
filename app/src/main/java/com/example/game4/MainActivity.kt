package com.example.game4

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val BackGround = findViewById<View>(R.id.BackGround)
        val StolbUp = findViewById<View>(R.id.StolbUp)
        val StolbDown = findViewById<View>(R.id.StolbDown)
        val textview = findViewById<TextView>(R.id.textView)
        val bird = findViewById<View>(R.id.bird)
        var nul = 0
        var scpre = 0
        var speed = 6000

        StolbDown.animate().translationX(1000.toFloat()).setDuration(0)
        StolbUp.animate().translationX(1000.toFloat()).setDuration(0)

        fun Up(){
            bird.animate().translationYBy(-200.toFloat()).setDuration(100).withEndAction() {
                bird.animate().translationYBy(2000.toFloat()).setDuration(2000).withEndAction() {
                }
            }
        }
        fun StolbRun() {
            StolbDown.animate().translationX(-1200.toFloat()).setDuration(speed.toLong()).withEndAction() {
                StolbDown.animate().translationX(1000.toFloat()).setDuration(0).withEndAction() {
                    StolbRun()
                }
            }
            StolbUp.animate().translationX(-1200.toFloat()).setDuration(speed.toLong()).withEndAction() {
                StolbUp.animate().translationX(1000.toFloat()).setDuration(0)
            }

                if (speed >= 200){
                speed -= 200
                Log.e("sdsad", "$speed")
                scpre++
                textview.text = "Lvl: " + scpre
            }
                else if (speed <= 200){
                    onRestart()
                }
            }

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {

                val otherViewRect1 = Rect()
                StolbDown.getHitRect(otherViewRect1)

                val otherViewRect2 = Rect()
                StolbUp.getHitRect(otherViewRect2)

                val myViewRect = Rect()
                bird.getHitRect(myViewRect)
                if (Rect.intersects(myViewRect, otherViewRect1)) {
                    bird.animate().cancel()
                    StolbDown.animate().cancel()
                    StolbUp.animate().cancel()
                    textview.text = "Game Over"
                    textview.animate().alpha(1.0f).setDuration(0)
                }
                if (Rect.intersects(myViewRect, otherViewRect2)) {
                    bird.animate().cancel()
                    StolbDown.animate().cancel()
                    StolbUp.animate().cancel()
                    textview.text = "Game Over"
                   onRestart()
                }
                handler.postDelayed(this::run, 1)
            }
        })
        handler.post(object : Runnable {
            override fun run() {
                if (bird.translationY >= 1000){
                    bird.animate().translationYBy(-100f).setDuration(0)
                }
                if (bird.translationY <= - 1100){
                    bird.animate().translationYBy(200f).setDuration(0).withEndAction() {
                        bird.animate().translationYBy(2000.toFloat()).setDuration(2000)
                    }
                }
                    handler.postDelayed(this::run, 1)

            }
        })


        BackGround.setOnClickListener(){
            Up()

            if (nul == 0) {
                StolbRun()
                nul++
            }
        }
    }
}