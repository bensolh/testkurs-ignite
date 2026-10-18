package com.example.testkursignite.exercise5

import java.time.LocalDate

enum class OrderStatus { PLACED, SHIPPED, DELIVERED, CANCELLED }

data class Order(
    val id: String,
    val customerId: String,
    val total: Int,
    val status: OrderStatus = OrderStatus.PLACED,
    val deliveredOn: LocalDate? = null,
) {

    fun ship(): Order {
        check(status == OrderStatus.PLACED) { "Only placed orders can be shipped" }
        return copy(status = OrderStatus.SHIPPED)
    }

    fun deliver(on: LocalDate): Order {
        check(status == OrderStatus.SHIPPED) { "Only shipped orders can be delivered" }
        return copy(status = OrderStatus.DELIVERED, deliveredOn = on)
    }

    fun cancel(): Order {
        check(status == OrderStatus.PLACED) { "Only placed orders can be cancelled" }
        return copy(status = OrderStatus.CANCELLED)
    }

    fun canBeReturned(today: LocalDate): Boolean =
        status == OrderStatus.DELIVERED &&
            deliveredOn != null &&
            !today.isAfter(deliveredOn.plusDays(RETURN_DAYS))

    companion object {
        const val RETURN_DAYS = 30L
    }
}

interface OrderRepository {
    fun findById(id: String): Order?
}

class ReturnService(private val repository: OrderRepository) {

    fun canReturn(orderId: String, today: LocalDate): Boolean {
        val order = repository.findById(orderId) ?: throw IllegalArgumentException("Order $orderId not found")
        return order.canBeReturned(today)
    }
}

