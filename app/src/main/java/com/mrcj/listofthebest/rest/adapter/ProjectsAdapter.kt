package com.mrcj.listofthebest.rest.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mrcj.listofthebest.R
import com.mrcj.listofthebest.extesions.inflate
import com.mrcj.listofthebest.model.Project
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.project_item.view.*
import java.io.InputStream
import java.net.URL


class ProjectsAdapter() : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    var projects : ArrayList<Project> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    inner class ProjectViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(project: Project) = with(itemView) {
            Picasso.with(itemView.context).load(project.owner.url_photo).into(itemView.imageView)
            itemView.tx_name.text = project.owner.name
            itemView.tx_name_project.text = project.name
            itemView.tx_description.text = project.description

            itemView.chip_fork.text = "Forks - " + project.forks_count
            itemView.chip_star.text = "Stars - " + project.stargazers_count

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProjectViewHolder(parent)

    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    companion object {
        private const val VIEW_ID =
            R.layout.project_item
    }

}