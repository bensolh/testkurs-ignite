package com.example.testkursignite.exercise4.solution

import com.example.testkursignite.exercise4.Order
import com.example.testkursignite.exercise4.OrderRepository

class FakeOrderRepository : OrderRepository {

    val orders = mutableListOf<Order>()

    override fun findAll(): List<Order> = orders.toList()

    override fun findByCustomerId(customerId: String): List<Order> =
        orders.filter { it.customerId == customerId }
}

