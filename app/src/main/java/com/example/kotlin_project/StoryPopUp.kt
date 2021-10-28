package com.example.kotlin_project

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlin_project.adapters.StoryAdapter
import com.example.kotlin_project.models.Photo

class StoryPopUp(private val adapter: StoryAdapter ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.image_popup)
        setupComponents()
    }

    private fun setupComponents() {
//        val storyImage = findViewById<ImageView>(R.id.image_itemm)
//        storyImage.setImageURI(Uri.parse(story.url.full))
        //Glide.with(adapter.context).load(Uri.parse(story.url.full)).into(storyImage)
    }

}