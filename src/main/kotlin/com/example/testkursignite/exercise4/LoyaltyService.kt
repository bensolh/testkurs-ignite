package com.example.testkursignite.exercise4

enum class OrderStatus { PLACED, DELIVERED, CANCELLED }

data class Order(val id: String, val customerId: String, val total: Int, val status: OrderStatus) {

    fun loyaltyPoints(): Int = if (status == OrderStatus.DELIVERED) total / 10 else 0
}

interface OrderRepository {
    fun findAll(): List<Order>
    fun findByCustomerId(customerId: String): List<Order>
}

class LoyaltyService(private val repository: OrderRepository) {

    fun pointsFor(customerId: String): Int =
        repository.findAll()
            .filter { it.customerId == customerId }
            .sumOf { it.loyaltyPoints() }
}

