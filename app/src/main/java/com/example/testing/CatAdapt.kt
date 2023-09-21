package com.example.testing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews.RemoteCollectionItems
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapt : RecyclerView.Adapter<CatAdapt.CatViewHolder>(){
private var catList: ArrayList<Cat> = ArrayList()

    fun addItems(items: ArrayList<Cat>){
        this.catList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_cat,parent,false)

    )

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = catList[position]
        holder.bindView(cat)
    }

    class CatViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var title = view.findViewById<TextView>(R.id.tvTitle)
        private var tvNum = view.findViewById<TextView>(R.id.tvNum)
        private var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(cat: Cat){
            id.text = cat.id.toString()
                title.text =cat.title.toString()
                tvNum.text =cat.animalNum.toString()
        }
    }


}