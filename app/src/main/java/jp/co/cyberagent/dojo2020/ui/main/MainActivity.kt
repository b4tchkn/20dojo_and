package jp.co.cyberagent.dojo2020.ui.main
import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R

class MainActivity :Activity(){


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_page)

        val a = resources.getStringArray(R.array.a).toMutableList()

        viewAdapter = RecyclerAdapter(a)
        viewManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_View).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

    }
}