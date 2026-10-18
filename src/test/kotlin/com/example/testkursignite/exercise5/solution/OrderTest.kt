package com.example.testkursignite.exercise5.solution

import com.example.testkursignite.exercise5.solution.OrderMother.cancelledOrder
import com.example.testkursignite.exercise5.solution.OrderMother.deliveredOrder
import com.example.testkursignite.exercise5.solution.OrderMother.shippedOrder
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OrderTest {

    private val deliveryDate = LocalDate.of(2026, 3, 1)

    @Nested
    inner class CanBeReturned {

        @Test
        fun `is true on the last day of the return period`() {
            val order = deliveredOrder(on = deliveryDate)

            assertThat(order.canBeReturned(today = deliveryDate.plusDays(30))).isTrue()
        }

        @Test
        fun `is false after the return period`() {
            val order = deliveredOrder(on = deliveryDate)

            assertThat(order.canBeReturned(today = deliveryDate.plusDays(31))).isFalse()
        }

        @Test
        fun `is false for orders not yet delivered`() {
            val order = shippedOrder()

            assertThat(order.canBeReturned(today = deliveryDate)).isFalse()
        }

        @Test
        fun `is false for cancelled orders`() {
            val order = cancelledOrder()

            assertThat(order.canBeReturned(today = deliveryDate)).isFalse()
        }
    }

    @Nested
    inner class Cancel {

        @Test
        fun `fails for delivered orders`() {
            val order = deliveredOrder(on = deliveryDate)

            assertThatThrownBy { order.cancel() }.isInstanceOf(IllegalStateException::class.java)
        }
    }
}

