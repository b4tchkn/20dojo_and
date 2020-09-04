package jp.co.cyberagent.dojo2020

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.reflect.Array.get
import java.util.*
import kotlin.concurrent.schedule


class TimerFragment: Fragment(){



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.timer_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("I/System.out","ViewCreated")

        //setContentView(R.layout.timer_tab)

        val timeCountTextView = view.findViewById<TextView>(R.id.timeCountTextView)
        var time: Int = 1
        time += 1023
        timeCountTextView.text = time.toString()
        timeCountTextView.setTextColor(Color.argb(128, 255, 128, 255))

        val temp = TimerViewModel(100)

        Log.d("VMint", temp.vmtime.toString())
    }
}