package com.example.felinefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.felinefinder.databinding.ActivityCatsDetailBinding

class CatsDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_CAT = "cat"

    }

    private lateinit var binding: ActivityCatsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent?.getParcelableExtra<Cat>(EXTRA_CAT)
        binding.textViewDetailCatName.text = hero?.name
        binding.textViewDetailDescription.text = hero?.description
        binding.textViewDetailFriendly.text = hero?.friendly
        binding.textViewDetailBreed.text = hero?.breed
        val heroName = ""

        //how do you upload diff images?
//        val heroDrawable = getDrawable(resources.getIdentifier(hero?.image, "drawable", packageName))
//        binding.imageViewDetailPicture.setImageDrawable(heroDrawable)
    }
}