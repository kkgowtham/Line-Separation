package com.demo.lineseparation


import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InvoiceListingAdapter(
    private val context: Activity,
    private val invoiceListItems: List<EDIJsonValidate>,
    val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<InvoiceListingAdapter.InvoiceHolder>() {

    class InvoiceHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceHolder {
        return InvoiceHolder(
            LayoutInflater.from(context).inflate(R.layout.single_item_layout, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InvoiceHolder, position: Int) {
        val currentData = invoiceListItems[position]
        val deleteBtn = holder.view.findViewById<ImageView>(R.id.delete)
        val printBtn = holder.view.findViewById<ImageView>(R.id.print)
        val relayDoc = holder.view.findViewById<View>(R.id.relayDoc)
        val documentNoTv: TextView = holder.view.findViewById(R.id.documentNoTV)
        val sameNext =
            (position != invoiceListItems.size - 1) && ((invoiceListItems[position].doc) == invoiceListItems[position + 1].doc)
        if (sameNext) {
            relayDoc.visibility = View.GONE
            deleteBtn.visibility = View.GONE
            printBtn.visibility = View.GONE
        } else {
            relayDoc.visibility = View.VISIBLE
        }
        val samePrevious =
            (position != 0) && ((invoiceListItems[position]).doc == invoiceListItems[position - 1].doc)
        if (samePrevious) {
            documentNoTv.visibility = View.GONE
            val previousData = invoiceListItems[position - 1]
            if (isOpen(currentData) || isOpen(previousData)) {
                showPrint(false, printBtn, deleteBtn)
            }else{
                showPrint(true,printBtn,deleteBtn)
            }
        } else {
            documentNoTv.visibility = View.VISIBLE
        }
        if (!sameNext && !samePrevious) {
            if (isOpen(currentData)) {
                showPrint(false, printBtn, deleteBtn)
            } else if (isClosed(currentData)) {
                showPrint(true, printBtn, deleteBtn)
            }
        }
        documentNoTv.text = "Document No:${currentData.doc}"
        holder.view.findViewById<TextView>(R.id.invoiceNoTv).text =
            "Document Name No:${currentData.docName}"
        holder.view.findViewById<TextView>(R.id.descriptionTv).text = "Line:${currentData.Line}"

    }

    override fun getItemCount(): Int {
        return invoiceListItems.size
    }

    interface OnItemClickListener {
        fun unlistClick(invoiceNo: String)
        fun printClick(invoiceNo: String)
    }

    private fun isOpen(data: EDIJsonValidate): Boolean {
        return data.status == "open"
    }

    private fun isClosed(data: EDIJsonValidate): Boolean {
        return data.status == "closed"
    }

    private fun showPrint(show: Boolean, printBtn: ImageView, deleteBtn: ImageView) {
        if (show) {
            deleteBtn.visibility = View.GONE
            printBtn.visibility = View.VISIBLE
        } else {
            deleteBtn.visibility = View.VISIBLE
            printBtn.visibility = View.GONE
        }
    }
}
