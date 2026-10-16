package com.example.testkursignite.exercise2

enum class OrderStatus { PLACED, SHIPPED, CANCELLED }

data class Order(val id: String, val customerId: String, val total: Int, val status: OrderStatus) {

    fun cancel(): Order {
        check(status == OrderStatus.PLACED) { "Only placed orders can be cancelled" }
        return copy(status = OrderStatus.CANCELLED)
    }
}

interface OrderRepository {
    fun findById(id: String): Order?
    fun save(order: Order)
}

class OrderService(private val repository: OrderRepository) {

    fun cancel(orderId: String): Order {
        val order = repository.findById(orderId) ?: throw IllegalArgumentException("Order $orderId not found")
        val cancelled = order.cancel()
        repository.save(cancelled)
        return cancelled
    }
}

