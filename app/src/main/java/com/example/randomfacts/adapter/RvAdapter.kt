package com.example.randomfacts.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomfacts.R
import com.example.randomfacts.databinding.RvRowBinding
import com.example.randomfacts.model.DataModel
import com.example.randomfacts.util.List
import com.example.randomfacts.util.SharedPre

class RvAdapter(var context: Context, var list: ArrayList<DataModel>) :
    RecyclerView.Adapter<RvAdapter.ItemHolder>() {
    val bookmarksList: ArrayList<String> = ArrayList();
//    var movieList = ArrayList<DataModel>()
//
//    fun setUpdatedData(movieList: ArrayList<DataModel>) {
//        this.movieList = movieList
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        context = parent.context

        val inflater = LayoutInflater.from(context);
        val view = RvRowBinding.inflate(
            inflater,
            parent,
            false
        )

        return ItemHolder(view);
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.view.txtTitle.text = list[position].title;
        holder.view.txtFact.text = list[position].fact;

        val url =
            "https://apps-images.s3-us-west-2.amazonaws.com/facts-images/${list[position].id}.jpg"
        Glide.with(context)
            .load(url)
            .into(holder.view.ivFacts)

        val shared = SharedPre()

        val getList = shared.getList(context);
        holder.view.ivBookmarks.setOnClickListener {
            if (List.list.size == 0 && getList.size != 0) {
                List.list = getList
            }

            List.list.add(list[position].id.toString())

            shared.setLists(context, List.list)
            Toast.makeText(context, "Kaydedildi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class ItemHolder(var view: RvRowBinding) : RecyclerView.ViewHolder(view.root) {

    }


}