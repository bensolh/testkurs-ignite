package com.example.testkursignite.exercise3.solution

import com.example.testkursignite.exercise3.Order
import com.example.testkursignite.exercise3.OrderStatus
import com.example.testkursignite.exercise3.ShippingService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ShippingServiceTest {

    private val emailSender = FakeEmailSender()
    private val service = ShippingService(emailSender)

    @Nested
    inner class Ship {

        @Test
        fun `marks the order as shipped`() {
            val shipped = service.ship(order(status = OrderStatus.PLACED))

            assertThat(shipped.status).isEqualTo(OrderStatus.SHIPPED)
        }

        @Test
        fun `notifies the customer`() {
            service.ship(Order("order-1", "kari@example.com", OrderStatus.PLACED))

            assertThat(emailSender.sentEmails)
                .containsExactly(SentEmail("kari@example.com", "Your order order-1 has been shipped"))
        }

        @Test
        fun `does not notify the customer when the order is cancelled`() {
            assertThatThrownBy { service.ship(order(status = OrderStatus.CANCELLED)) }
                .isInstanceOf(IllegalStateException::class.java)

            assertThat(emailSender.sentEmails).isEmpty()
        }
    }

    private fun order(status: OrderStatus) = Order("order-1", "kari@example.com", status)
}

