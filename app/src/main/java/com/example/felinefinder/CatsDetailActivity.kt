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

        val cat = intent?.getParcelableExtra<Cat>(EXTRA_CAT)
        binding.textViewDetailCatName.text = cat?.name
        binding.textViewDetailDescription.text = cat?.description
        binding.textViewDetailFriendly.text = cat?.friendly
        binding.textViewDetailBreed.text = cat?.breed
        binding.textViewDetailLastSeen.text = cat?.lastSeen.toString()
        val catName = ""

        //how do you upload diff images?
        val catDrawable = getDrawable(resources.getIdentifier(cat?.image, "drawable", packageName))
        binding.imageViewDetailPicture.setImageDrawable(catDrawable)
    }
}