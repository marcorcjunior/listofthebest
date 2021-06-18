package com.mrcj.listofthebest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mrcj.listofthebest.model.Projects
import com.mrcj.listofthebest.rest.LoadState
import com.mrcj.listofthebest.rest.Repositories
import com.mrcj.listofthebest.rest.adapter.LoadStateAdapter
import com.mrcj.listofthebest.rest.adapter.ProjectsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val urlBase : String = "https://api.github.com"
    var page : Int = 1

    private lateinit var simpleTextAdapter: ProjectsAdapter
    private lateinit var loadStateAdapter: LoadStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        addItems()
    }


    private fun addItems() {
        val callback = RetrofitUtils.getRetrofitInstance(urlBase)
            .create(Repositories::class.java)
            .getList("language:kotlin", "stars", page)

        callback.enqueue(object : Callback<Projects> {
            override fun onFailure(call: Call<Projects>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.d(Log.VERBOSE.toString(), t.message.toString())
            }

            override fun onResponse(call: Call<Projects>, response: Response<Projects>) {
                Log.d(Log.VERBOSE.toString(), response.body().toString())
                simpleTextAdapter.projects = ArrayList(response.body()!!.items)
            }
        })
    }

    private fun setupRecyclerView() = with(rv_list_projects) {
        simpleTextAdapter = ProjectsAdapter()
        loadStateAdapter = LoadStateAdapter()

        layoutManager = LinearLayoutManager(this@MainActivity)

        adapter = MergeAdapter(
            simpleTextAdapter,
            loadStateAdapter
        )

        rv_list_projects.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                    postDelayed(
                        {
                            page += 1
                            addItems()
                        },
                        1000
                    )
                }
            }
        })
    }

}