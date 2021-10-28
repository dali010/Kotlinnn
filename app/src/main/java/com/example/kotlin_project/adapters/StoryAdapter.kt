package com.example.kotlin_project.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_project.R
import com.example.kotlin_project.StoryPopUp
import com.example.kotlin_project.models.Photo
import com.lcodecore.tkrefreshlayout.header.progresslayout.CircleImageView

class StoryAdapter (private val photos: MutableList<Photo>, val  context: Context) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val story = view.findViewById<ImageView>(R.id.story)
        val userNameStory : TextView = view.findViewById(R.id.userNameStory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).
            inflate(R.layout.item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(photos[position].url.regular)
            .placeholder(ColorDrawable(Color.parseColor(photos[position].color)))
            .into(holder.story)
        holder.userNameStory.text = photos[position].user.username

        val currentPhoto = photos[position]

        holder.itemView.setOnClickListener(){
            StoryPopUp(this).show()
        }

    }

    override fun getItemCount(): Int {
        return photos.size
    }

//        val item = itemsList[position]
//        holder.story.setImageResource(item)




}