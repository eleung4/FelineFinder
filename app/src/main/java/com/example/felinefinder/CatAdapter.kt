package com.example.felinefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(var dataSet: List<String>) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewDesc : TextView
        val textViewFriendly : TextView
        val layout : ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View
            textViewName = view.findViewById(R.id.textView_catitem_name)
            textViewDesc = view.findViewById(R.id.textView_catitem_descr)
            textViewFriendly = view.findViewById(R.id.textView_catitem_friendly)
            layout = view.findViewById(R.id.layout_catItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main, viewGroup, false)

        return ViewHolder(view) //weeee
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        val cat = dataSet[position]
//        viewHolder.textViewName.text = cat.name
//        viewHolder.textViewDesc.text = cat.description
//        viewHolder.textViewFriendly.text = cat.friendly
//        viewHolder.layout.setOnClickListener {
//            Toast.makeText(it.context, cat.toString(), Toast.LENGTH_SHORT).show()
//            //make intent to open new activity
//            val detailIntent = Intent(it.context, CatsDetailActivity::class.java)
//            detailIntent.putExtra(CatsDetailActivity.EXTRA_CAT, cat)
//            it.context.startActivity(detailIntent)
//        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}