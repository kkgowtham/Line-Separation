package com.demo.lineseparation

data class InvoiceData(
    var LineItem: List<EDIJsonValidate>? = ArrayList()
)