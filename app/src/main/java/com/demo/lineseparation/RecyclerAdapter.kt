package com.demo.lineseparation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var context:Context,var list:List<DataModel>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dataModel = list[position]
        val hideLine = (position!=list.size-1)&&((list[position].docNo) == list[position+1].docNo)
        holder.view.findViewById<TextView>(R.id.documentNoTV).text= "Document No:${dataModel.docNo}"
        holder.view.findViewById<TextView>(R.id.invoiceNoTv).text= "Invoice No:${dataModel.invoiceNumber}"
        holder.view.findViewById<TextView>(R.id.descriptionTv).text= "Description:${dataModel.description}"
        if (hideLine){
            holder.view.findViewById<View>(R.id.relayDoc).visibility = View.GONE
        }
    }
}