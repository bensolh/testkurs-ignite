package com.example.testkursignite.exercise4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class LoyaltyServiceTest {

    private val repository = mock(OrderRepository::class.java)
    private val service = LoyaltyService(repository)

    @Test
    fun `points are earned for delivered orders only`() {
        `when`(repository.findAll()).thenReturn(
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

