package jp.co.cyberagent.dojo2020

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.reflect.Array.get
import java.util.*
import kotlin.concurrent.schedule


class TimerFragment: Fragment(){



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.timer_tab, container, false)
    }

    private val handler = Handler()
    private var runnable = Runnable {}
    var i = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("I/System.out","ViewCreated")

        //setContentView(R.layout.timer_tab)

        val timeCountTextView = view.findViewById<TextView>(R.id.timeCountTextView)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val stopButton = view.findViewById<Button>(R.id.stopButton)
        var time: Int = 1
        time += 1023
        timeCountTextView.text = time.toString()
        timeCountTextView.setTextColor(Color.argb(255, 255, 128, 255))

        runnable = Runnable {
            i++
            timeCountTextView.text = i.toString()
            handler.postDelayed(runnable, 1000)
        }
        handler.post(runnable)


        Log.d("VMint", TimerViewModel().vmtime.toString())
        startButton.setOnClickListener {
            timeCountTextView.text = TimerViewModel().vmtime.toString()
        }

        stopButton.setOnClickListener {
            time ++
            timeCountTextView.text = time.toString()
        }
    }


}