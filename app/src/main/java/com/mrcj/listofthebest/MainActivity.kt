package com.mrcj.listofthebest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.activity_main.*

import com.mrcj.listofthebest.extesions.visible
import com.mrcj.listofthebest.model.Projects
import com.mrcj.listofthebest.rest.LoadState
import com.mrcj.listofthebest.rest.Repositories
import com.mrcj.listofthebest.rest.adapter.LoadStateAdapter
import com.mrcj.listofthebest.rest.adapter.ProjectsAdapter


class MainActivity : AppCompatActivity() {
    private val urlBase : String = "https://api.github.com"
    var page : Int = 1

    private lateinit var projectsAdapter: ProjectsAdapter
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
                load_open.visible(false)
                Snackbar.make(cl_list, t.message.toString(), Snackbar.LENGTH_LONG).show()
                Log.d(Log.VERBOSE.toString(), t.message.toString())
            }

            override fun onResponse(call: Call<Projects>, response: Response<Projects>) {
                load_open.visible(false)
                if(response.code() == 200) {
                    Log.d(Log.VERBOSE.toString(), response.body().toString())
                    projectsAdapter.projects = ArrayList(response.body()!!.items!!)
                } else {
                    Snackbar.make(cl_list, "Houve um erro que nao pode ser tratado!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") {
                            addItems()
                        }
                        .show()
                }
            }
        })
    }

    private fun setupRecyclerView() = with(rv_list_projects) {
        projectsAdapter = ProjectsAdapter()
        loadStateAdapter = LoadStateAdapter()

        layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        adapter = MergeAdapter(projectsAdapter, loadStateAdapter)

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                    postDelayed({
                            page += 1
                            addItems()
                        },1000)
                }
            }
        })
    }

}