package com.example.testkursignite.exercise1.solution

import com.example.testkursignite.exercise1.solution.OrderMother.customer
import com.example.testkursignite.exercise1.solution.OrderMother.line
import com.example.testkursignite.exercise1.solution.OrderMother.order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrderTest {

    @Nested
    inner class ShippingCost {

        @Test
        fun `is charged when subtotal is below the limit`() {
            val order = order(lines = listOf(line(price = 499)))

            assertThat(order.shippingCost()).isEqualTo(79)
        }

        @Test
        fun `is free when subtotal reaches the limit`() {
            val order = order(lines = listOf(line(price = 500)))

            assertThat(order.shippingCost()).isZero()
        }

        @Test
        fun `is free for members`() {
            val order = order(customer = customer(isMember = true), lines = listOf(line(price = 100)))

            assertThat(order.shippingCost()).isZero()
        }
    }

    @Nested
    inner class Total {

        @Test
        fun `is the sum of all lines plus shipping`() {
            val order = order(lines = listOf(line(price = 100, quantity = 2), line(price = 50)))

            assertThat(order.total()).isEqualTo(250 + 79)
        }
    }
}

