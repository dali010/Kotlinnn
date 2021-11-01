package com.example.kotlin_project.fragments

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_project.R
import com.example.kotlin_project.adapters.ImageItemAdapter
import com.example.kotlin_project.databinding.ActivityMainBinding
import com.example.kotlin_project.databinding.FragmentHomeBinding
import com.example.kotlin_project.models.Photo
import com.example.kotlin_project.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
//    private lateinit var binding1 : ActivityMainBinding
    private lateinit var adapter : ImageItemAdapter
    private lateinit var adapter1 : ImageItemAdapter
    private var page : Int = 1
    private var filteredList : MutableList<Photo> = ArrayList()
    var search = view?.findViewById<EditText>(R.id.search)

    private var photos : MutableList<Photo> = ArrayList()
    private var sort : String = "popular"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
//        val view1 = inflater.inflate(R.layout.activity_main,container,false)
        binding = FragmentHomeBinding.bind(view)
//        binding1 = ActivityMainBinding.bind(view1)

        initRecyclerView()
        getImages()

        return view
    }



    private fun getImages() {
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
                    adapter.notifyDataSetChanged()
                }
                else
                    Log.d("response",response.body().toString())
            }

            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                Log.d("Response","Failed")
            }

        })

    }

    private fun initRecyclerView() {

        binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = ImageItemAdapter(photos,requireContext())
        binding.homeRecyclerView.adapter = adapter


        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("text",s.toString())

                filteredList.clear()
                if (s.toString().isEmpty()){
                    binding.homeRecyclerView.adapter=adapter
                    adapter.notifyDataSetChanged()
                }
                else {
                    //Filter(s.toString())
                    for ( photo in photos){
                        if (photo.user.username.contains(s.toString()) ){
                            filteredList.add(photo)
                        }
                    }
                    adapter1 = ImageItemAdapter(filteredList,requireContext())
                    binding.homeRecyclerView.adapter = adapter1
                    adapter1.notifyDataSetChanged()

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

    }

    private fun Filter(toString: String) {
        for ( photo in photos){
            if (photo.user.username == toString){
                filteredList.add(photo)
            }
        }
        adapter1 = ImageItemAdapter(filteredList,requireContext())
        binding.homeRecyclerView.adapter = adapter1
        adapter1.notifyDataSetChanged()

    }
}