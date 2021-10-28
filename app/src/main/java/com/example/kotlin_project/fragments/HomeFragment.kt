package com.example.kotlin_project.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_project.R
import com.example.kotlin_project.adapters.StoryAdapter
import com.example.kotlin_project.models.Photo
import com.example.kotlin_project.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var page : Int = 1
    private var photos : MutableList<Photo> = ArrayList()
    private var sort : String = "popular"
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_header, container , false)

        //recuperer le recycle view
        val horizontalRecycleView = view.findViewById<RecyclerView>(R.id.horizontal_recycle_view)
        storyAdapter = StoryAdapter(photos, requireContext())
        horizontalRecycleView.adapter = storyAdapter
        prepareItems()
        return view
    }

    private fun prepareItems() {
        page = 1
        val getPost = RetrofitInstance.api.getRecentPhotos(page,30,sort)
        getPost.enqueue(object : Callback<MutableList<Photo>> {
            override fun onResponse(
                call: Call<MutableList<Photo>>,
                response: Response<MutableList<Photo>>
            ) {
                if(response.isSuccessful)
                {
                    photos.clear()

                    response.body()?.let { photos.addAll(it)}
                    storyAdapter.notifyDataSetChanged()
                }
                else
                    Log.d("response",response.body().toString())
            }

            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                Log.d("Response","Failed")
            }

        })

    }
}