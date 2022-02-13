package by.varyvoda.matvey.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import by.varyvoda.matvey.R
import by.varyvoda.matvey.domain.BringCounter

class Floppa : Fragment() {

    private lateinit var feedBtn: Button
    private lateinit var feedCountView: TextView
    private lateinit var floppaView: ImageView

    private val counter: BringCounter = BringCounter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.floppa, container, false)

        feedBtn = view.findViewById(R.id.feedBtn)
        feedCountView = view.findViewById(R.id.feedCount)
        floppaView = view.findViewById(R.id.floppa)

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

        return view
    }

    private fun updateText() {
        feedCountView.text = getString(R.string.count, counter.count)
    }
}