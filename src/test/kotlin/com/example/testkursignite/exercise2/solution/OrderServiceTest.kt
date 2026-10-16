package com.example.testkursignite.exercise2.solution

import com.example.testkursignite.exercise2.Order
import com.example.testkursignite.exercise2.OrderService
import com.example.testkursignite.exercise2.OrderStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private val repository = FakeOrderRepository()
    private val service = OrderService(repository)

    @Nested
    inner class Cancel {

        @Test
        fun `stores the order as cancelled`() {
            repository.save(order(id = "order-1", status = OrderStatus.PLACED))

            service.cancel("order-1")

            assertThat(repository.findById("order-1")?.status).isEqualTo(OrderStatus.CANCELLED)
        }

        @Test
        fun `fails and leaves the order unchanged when it is already shipped`() {
            repository.save(order(id = "order-1", status = OrderStatus.SHIPPED))

            assertThatThrownBy { service.cancel("order-1") }
                .isInstanceOf(IllegalStateException::class.java)
            assertThat(repository.findById("order-1")?.status).isEqualTo(OrderStatus.SHIPPED)
        }

        @Test
        fun `fails when the order does not exist`() {
            assertThatThrownBy { service.cancel("unknown") }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    private fun order(id: String, status: OrderStatus) = Order(id, "customer-1", 250, status)
}

