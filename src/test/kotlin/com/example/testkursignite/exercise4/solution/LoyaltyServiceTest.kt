package com.example.testkursignite.exercise4.solution

import com.example.testkursignite.exercise4.LoyaltyService
import com.example.testkursignite.exercise4.Order
import com.example.testkursignite.exercise4.OrderStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LoyaltyServiceTest {

    private val repository = FakeOrderRepository()
    private val service = LoyaltyService(repository)

    @Test
    fun `points are earned for delivered orders only`() {
        repository.orders.addAll(
            listOf(
                Order("order-1", "customer-1", 1000, OrderStatus.DELIVERED),
                Order("order-2", "customer-1", 500, OrderStatus.CANCELLED),
                Order("order-3", "customer-2", 2000, OrderStatus.DELIVERED),
            )
        )

        val points = service.pointsFor("customer-1")

        assertThat(points).isEqualTo(100)
    }
}

