package com.example.kotlin_project.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.example.kotlin_project.*
import com.example.kotlin_project.models.Photo
import kotlin.math.log

class ImageItemAdapter(private val photos: MutableList<Photo>, val  context: Context) :
    RecyclerView.Adapter<ImageItemAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            val photo : ImageView = itemView.findViewById(R.id.photo)
            val userName : TextView = itemView.findViewById(R.id.userName)
            val createdAt: TextView = itemView.findViewById(R.id.createdAt)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(context)
            .load(photos[position].url.regular)
            .placeholder(ColorDrawable(Color.parseColor(photos[position].color)))
            .into(holder.photo)

        holder.userName.text = photos[position].user.username
        holder.createdAt.text = photos[position].created_at.substring(0,10)

        holder.itemView.setOnClickListener(){
            unsplashPopUp(this,photos[position]).show()
        }
       // Log.d("wiiiw",holder.textt.text.toString())


       // Glide.with(context)
            //.load(photos[position].user.profileImage.medium)
            //.placeholder(ColorDrawable(Color.parseColor(photos[position].color)))
           // .into(holder.userLogo)

    }

    override fun getItemCount(): Int {
        return photos.size
    }
}