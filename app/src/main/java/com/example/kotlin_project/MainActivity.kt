package com.example.kotlin_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_project.adapters.ViewPagerAdapter
import com.example.kotlin_project.databinding.ActivityMainBinding
import com.example.kotlin_project.fragments.Home
import com.example.kotlin_project.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewPagerAdapter(this)
        val tableNames = arrayOf("Home","Category")
        adapter.addFragment(Home(),tableNames[0])
        binding.viewpagerId.adapter = adapter


    }
}