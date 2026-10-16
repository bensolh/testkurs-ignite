package com.example.testkursignite.exercise5_bonus.solution

import com.example.testkursignite.exercise5_bonus.Invoice
import com.example.testkursignite.exercise5_bonus.InvoiceService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class InvoiceServiceTest {

    private val today = LocalDate.of(2026, 3, 15)
    private val clock = Clock.fixed(Instant.parse("2026-03-15T12:00:00Z"), ZoneOffset.UTC)
    private val service = InvoiceService(clock)

    @Nested
    inner class OverdueInvoices {

        @Test
        fun `includes unpaid invoices past their due date`() {
            val invoice = Invoice("invoice-1", dueDate = today.minusDays(1), paid = false)

            assertThat(service.overdueInvoices(listOf(invoice))).containsExactly(invoice)
        }

        @Test
        fun `excludes invoices due today`() {
            val invoice = Invoice("invoice-1", dueDate = today, paid = false)

            assertThat(service.overdueInvoices(listOf(invoice))).isEmpty()
        }

        @Test
        fun `excludes paid invoices`() {
            val invoice = Invoice("invoice-1", dueDate = today.minusDays(10), paid = true)

            assertThat(service.overdueInvoices(listOf(invoice))).isEmpty()
        }
    }
}

