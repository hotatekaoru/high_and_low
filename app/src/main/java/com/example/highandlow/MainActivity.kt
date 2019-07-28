package com.example.highandlow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "high and low"
    private var yourCard = 0
    private var droidCard = 0
    private var hitCount = 0
    private var loseCount = 0
    private var gameStart = false
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        hitCount = 0
        loseCount = 0
        hitText.text = getString(R.string.hit_text)
        loseText.text = getString(R.string.lose_text)
        gameStart = true
        drawCard()
    }

    private fun drawCard() {
        yourCardImage.setImageResource(R.drawable.z02)
        droidCardImage.setImageResource(R.drawable.z01)

        yourCard = (1..13).random()
        Log.d(tag, "You:" + yourCard)

        val fileName = "d" + String.format("%02d", yourCard)
        val pictureId = resources.getIdentifier(fileName, "drawable", packageName)
        yourCardImage.setImageResource(pictureId)

        droidCard = (1..13).random()
        Log.d(tag, "Droid:" + droidCard)
        answered = false
    }
}
