package com.demo.lineseparation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(),InvoiceListingAdapter.OnItemClickListener {
    lateinit var recyclerView:RecyclerView
    var list = arrayListOf<EDIJsonValidate>()
    lateinit var adapter :InvoiceListingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        getDataFromAssets()?.let { list.addAll(it) }
        adapter = InvoiceListingAdapter(this, list,this)
        recyclerView.adapter = adapter
    }

    private fun getData(): ArrayList<DataModel> {
        val list = arrayListOf<DataModel>()
        list.add(DataModel("1","Hello 1","9889"))
        list.add(DataModel("2","Hello 2","8809"))
        list.add(DataModel("2","Hello 2A","9900"))
        list.add(DataModel("3","Hello 3","98821"))
        list.add(DataModel("4","Hello 4","2198821"))
        list.add(DataModel("4","Hello 4A","398821"))
        list.add(DataModel("5","Hello 5","45821"))
        return list
    }

    private fun getDataFromAssets(): List<EDIJsonValidate>? {
        applicationContext?.assets?.open("response.json")?.bufferedReader().use {
            val string = it?.readText()
            val invoiceData = Gson().fromJson(string,InvoiceData::class.java)
            return invoiceData?.LineItem
        }
    }

    override fun deleteClick(data: EDIJsonValidate) {
        list.remove(data)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, data.docName,Toast.LENGTH_LONG).show()
    }

    override fun printClick(data: EDIJsonValidate) {

    }
}