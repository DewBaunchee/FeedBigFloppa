package by.varyvoda.matvey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import by.varyvoda.matvey.domain.BringCounter

class MainActivity : AppCompatActivity() {

    private lateinit var feedBtn: Button
    private lateinit var feedCountView: TextView
    private lateinit var floppaView: ImageView
    private val counter: BringCounter = BringCounter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feedBtn = findViewById(R.id.feedBtn)
        feedCountView = findViewById(R.id.feedCount)
        floppaView = findViewById(R.id.floppa)

        feedBtn.setOnClickListener {
            counter.count()
        }

        updateText()
        counter.setBringListener {
            updateText()
            when (it % 15) {
                0 -> floppaView.setImageResource(R.mipmap.big_floppa)
                1 -> floppaView.setImageResource(R.mipmap.floppa_foreground)
            }
        }
    }

    private fun updateText() {
        feedCountView.text = getString(R.string.count, counter.count)
    }
}