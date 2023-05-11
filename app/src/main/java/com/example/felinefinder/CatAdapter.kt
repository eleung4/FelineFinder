package com.example.felinefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CatAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_item)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main, viewGroup, false)

        return RecyclerView.ViewHolder(view) //ee
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val cat = dataSet[position]
        viewHolder.textViewName.text = cat.name
        viewHolder.textViewDesc.text = cat.description
        viewHolder.textViewFriendly.text = cat.friendly
        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, cat.toString(), Toast.LENGTH_SHORT).show()
            //make intent to open new activity
            val detailIntent = Intent(it.context, CatsDetailActivity::class.java)
            detailIntent.putExtra(CatsDetailActivity.EXTRA_CAT, cat)
            it.context.startActivity(detailIntent)
        }
    }
}