package com.example.kotlin_project

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin_project.adapters.ImageItemAdapter
import com.example.kotlin_project.adapters.StoryAdapter
import com.example.kotlin_project.models.Photo

class unsplashPopUp(private val adapter: ImageItemAdapter, private val story: Photo) : Dialog(adapter.context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.image_popup_vertical)
        setupComponents()
    }

    private fun setupComponents() {
        val unsplashImage = findViewById<ImageView>(R.id.image_popUp_vertical)
        val small_description = findViewById<TextView>(R.id.small_description)
        val long_description = findViewById<TextView>(R.id.long_description)
        val created_at = findViewById<TextView>(R.id.created_at)
        val updated_at = findViewById<TextView>(R.id.updated_at)

        unsplashImage.setImageURI(Uri.parse(story.url.regular))
        Glide.with(adapter.context).load(Uri.parse(story.url.regular)).into(unsplashImage)

        small_description.text = story.description
        long_description.text = story.description
        created_at.text = story.created_at.substring(0,10)
        updated_at.text = story.updated_at.substring(0,10)
    }
}