package com.example.testkursignite.exercise2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class OrderServiceTest {

    private val repository = mock(OrderRepository::class.java)
    private val service = OrderService(repository)

    @Test
    fun `cancel order`() {
        val order = Order("order-1", "customer-1", 250, OrderStatus.PLACED)
        `when`(repository.findById("order-1")).thenReturn(order)

        val result = service.cancel("order-1")

        assertThat(result.status).isEqualTo(OrderStatus.CANCELLED)
    }

    @Test
    fun `cancel shipped order`() {
        val order = Order("order-1", "customer-1", 250, OrderStatus.SHIPPED)
        `when`(repository.findById("order-1")).thenReturn(order)

        assertThatThrownBy { service.cancel("order-1") }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `cancel unknown order`() {
        `when`(repository.findById("order-1")).thenReturn(null)

        assertThatThrownBy { service.cancel("order-1") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}

