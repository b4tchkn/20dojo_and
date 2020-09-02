package jp.co.cyberagent.dojo2020.ui.main
import kotlinx.android.synthetic.main.recyclerview_page.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R

class MainActivity : AppCompatActivity(),RecyclerViewHolder.ItemClickListner{

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_page)

        val a = resources.getStringArray(R.array.a).toMutableList()

        viewAdapter = RecyclerAdapter(this,this,a)
        viewManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView2).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext,"position $position was tapped",Toast.LENGTH_SHORT).show()
    }

}