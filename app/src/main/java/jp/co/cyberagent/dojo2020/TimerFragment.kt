package jp.co.cyberagent.dojo2020

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.timer_tab.*
import java.text.SimpleDateFormat
import java.util.*


class TimerFragment: Fragment(){

    // private val timer: Timer? = null
    private val handler = Handler()

    // private val timerText: TextView? = null
    private var delay: Long = 0
    private  var period:kotlin.Long = 0
    // private val count = 0

    private var timeValue = 0                              // 秒カウンター

    private val dataFormat = SimpleDateFormat("mm:ss.SS", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.timer_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delay = 0;
        period = 100;

        // Handler(スレット間通信：イベントキュー？)
        val runnable = object : Runnable {
            // メッセージ受信が有った時かな?
            override fun run() {
                timeValue++                      // 秒カウンタ+1
                timeToText(timeValue)?.let {        // timeToText()で表示データを作り
                    timeCountTextView.text = it            // timeText.textへ代入(表示)
                }
                handler.postDelayed(this, 1000)  // 1000ｍｓ後に自分にpost
            }
        }

        val startButton = view.findViewById<Button>(R.id.startButton);
        val stopButton = view.findViewById<Button>(R.id.stopButton);

        val timerText = view.findViewById<TextView>(R.id.timeCountTextView);

        timerText.setText(dataFormat.format(0));

        startButton.setOnClickListener{
            handler.post(runnable)
        }

        stopButton.setOnClickListener{
            handler.removeCallbacks(runnable)
        }

    }

    private fun timeToText(time: Int = 0): String? {
        return if (time < 0) {
            null                                    // 時刻が0未満の場合 null
        } else if (time == 0) {
            "00:00:00"                            // ０なら
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)  // 表示に整形
        }
    }

}