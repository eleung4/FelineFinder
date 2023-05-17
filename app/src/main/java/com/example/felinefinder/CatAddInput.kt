package com.example.felinefinder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.felinefinder.databinding.ActivityCatAddInputBinding

class CatAddInput : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_cat_add_input)
//    }
    private lateinit var binding: ActivityCatAddInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatAddInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: ""
//        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD) ?: ""

//        binding.editTextRegistrationUsername.setText(username)
//        binding.editTextRegistrationPassword.setText(password)

        //register an account and send back the username and password to the login activity to prefill those fields
        binding.buttonAddRegister.setOnClickListener {
            val name = binding.editTextAddName.text.toString()
            val breed = binding.editTextAddBreed.text.toString()
            val friendly = binding.editTextAddFriendly.text.toString()
            val lat = binding.editTextAddLat.text.toString()
            val long = binding.editTextAddLong.text.toString()

            MapFragment.addIcon(Double: lat, Double :long)
            //weeeee


        }


    }
}