package com.demo.lineseparation



import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InvoiceListingAdapter(val context: Activity, private var items: List<EDIJsonValidate>, val onItemClick: OnItemClickListener) : RecyclerView.Adapter<InvoiceListingAdapter.InvoiceHolder>() {
    private var invoiceListItems: List<EDIJsonValidate> = items

    class InvoiceHolder(var view:View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHolder {
        return InvoiceHolder(LayoutInflater.from(context).inflate(R.layout.single_item_layout,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InvoiceHolder, position: Int) {
        val data = invoiceListItems[position]
        val hideLine = (position!=invoiceListItems.size-1)&&((invoiceListItems[position].doc) == invoiceListItems[position+1].doc)
        val relayDoc  = holder.view.findViewById<View>(R.id.relayDoc)
            if (hideLine) {
                relayDoc.visibility = View.GONE
            } else {
                relayDoc.visibility = View.VISIBLE
            }
        holder.view.findViewById<TextView>(R.id.documentNoTV).text= "Document No:${data.doc}"
        holder.view.findViewById<TextView>(R.id.invoiceNoTv).text= "Document Name No:${data.docName}"
        holder.view.findViewById<TextView>(R.id.descriptionTv).text= "Line:${data.Line}"
    }

    override fun getItemCount(): Int {
        return invoiceListItems.size
    }

    interface OnItemClickListener {
        fun unlistClick(invoiceNo: String)
        fun printClick(invoiceNo: String)
    }
}
