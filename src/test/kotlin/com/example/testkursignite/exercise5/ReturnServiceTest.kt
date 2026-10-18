package com.example.testkursignite.exercise5

import com.example.testkursignite.exercise5.OrderMother.order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDate

class ReturnServiceTest {

    private val repository = mock(OrderRepository::class.java)
    private val service = ReturnService(repository)

    @Test
    fun `delivered order can be returned`() {
        val order = order(status = OrderStatus.DELIVERED, deliveredOn = LocalDate.of(2026, 3, 1))
        `when`(repository.findById("order-1")).thenReturn(order)

        val result = service.canReturn("order-1", LocalDate.of(2026, 3, 20))

        assertThat(result).isTrue()
    }

    @Test
    fun `delivered order cannot be returned after 30 days`() {
        val order = order(status = OrderStatus.DELIVERED, deliveredOn = LocalDate.of(2026, 3, 1))
        `when`(repository.findById("order-1")).thenReturn(order)

        val result = service.canReturn("order-1", LocalDate.of(2026, 4, 1))

        assertThat(result).isFalse()
    }

    @Test
    fun `shipped order cannot be returned`() {
        val order = order(status = OrderStatus.SHIPPED)
        `when`(repository.findById("order-1")).thenReturn(order)

        val result = service.canReturn("order-1", LocalDate.of(2026, 3, 20))

        assertThat(result).isFalse()
    }

    @Test
    fun `cancelled order cannot be returned`() {
        val order = order(status = OrderStatus.CANCELLED, deliveredOn = LocalDate.of(2026, 3, 1))
        `when`(repository.findById("order-1")).thenReturn(order)

        val result = service.canReturn("order-1", LocalDate.of(2026, 3, 20))

        assertThat(result).isFalse()
    }
}

