package com.example.felinefinder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class CatAddInput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_add_input)
    }
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: ""
        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD) ?: ""

        binding.editTextRegistrationUsername.setText(username)
        binding.editTextRegistrationPassword.setText(password)

        //register an account and send back the username and password to the login activity to prefill those fields
        binding.buttonRegistrationRegister.setOnClickListener {
            val name = binding.editTextRegistrationName.text.toString()
            val breed = binding.editTextRegistrationConfirmPassword.text.toString()
            val friendly = binding.editTextRegistrationUsername.text.toString()
            val lat = binding.editTextRegistrationName.text.toString()
            val long = binding.editTextTextEmailAddress.text.toString()

            MapFragment.addIcon(Double: lat, Double :long)


        }


    }
}