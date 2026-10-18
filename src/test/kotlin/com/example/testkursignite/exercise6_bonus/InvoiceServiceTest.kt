package com.example.testkursignite.exercise6_bonus

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.LocalDate

class InvoiceServiceTest {

    private val service = InvoiceService(Clock.systemDefaultZone())

    @Test
    fun `finds overdue invoices`() {
        val overdue = Invoice("invoice-1", LocalDate.now().minusDays(1), false)
        val dueToday = Invoice("invoice-2", LocalDate.now(), false)
        val paid = Invoice("invoice-3", LocalDate.now().minusDays(10), true)

        val result = service.overdueInvoices(listOf(overdue, dueToday, paid))

        assertThat(result).containsExactly(overdue)
    }
}

