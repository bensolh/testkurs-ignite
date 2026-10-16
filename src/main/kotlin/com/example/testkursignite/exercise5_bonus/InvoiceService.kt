package com.example.testkursignite.exercise5_bonus

import java.time.Clock
import java.time.LocalDate

data class Invoice(val id: String, val dueDate: LocalDate, val paid: Boolean) {

    fun isOverdue(today: LocalDate): Boolean = !paid && today.isAfter(dueDate)
}

class InvoiceService(private val clock: Clock) {

    fun overdueInvoices(invoices: List<Invoice>): List<Invoice> {
        val today = LocalDate.now(clock)
        return invoices.filter { it.isOverdue(today) }
    }
}

