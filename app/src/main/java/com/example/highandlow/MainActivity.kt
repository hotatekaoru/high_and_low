package com.example.highandlow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var yourCard = 0
    private var droidCard = 0
    private var hitCount = 0
    private var loseCount = 0
    private var gameStart = false
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highBtn.setOnClickListener {
            if(gameStart && !answered) {
                tapHighOrButton(true)
            }
        }

        lowBtn.setOnClickListener {
            if(gameStart && !answered) {
                tapHighOrButton(false)
            }
        }

        nextBtn.setOnClickListener {
            if(gameStart) {
                drawCard()
            }
        }
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

        val fileName = "d" + String.format("%02d", yourCard)
        val pictureId = resources.getIdentifier(fileName, "drawable", packageName)
        yourCardImage.setImageResource(pictureId)

        droidCard = (1..13).random()
        answered = false
    }

    private fun tapHighOrButton(answeredHigh: Boolean) {
        showDroidCard()
        answered = true

        val balance = yourCard - droidCard
        if (balance == 0) { return }

        val win = (balance > 0 && answeredHigh) || (balance < 0 && !answeredHigh)

        if (win) {
            val hitCount = hitCount++
            val hitCountText = hitCount.toString()
            hitText.text = "あなた $hitCountText"

            if (hitCount >= 5) {
                resultText.text = "あなたの勝ちです"
                gameStart = false
            }
        } else {
            loseCount++
            loseText.text = "あいて $loseCount"

            if (loseCount >= 5) {
                resultText.text = "あなたの負けです"
                gameStart = false
            }
        }
    }

    private fun showDroidCard() {
        val fileName = "c" + String.format("%02d", droidCard)
        val pictureId = resources.getIdentifier(fileName, "drawable", packageName)
        droidCardImage.setImageResource(pictureId)
    }
}
