package com.example.testkursignite.exercise2.solution

import com.example.testkursignite.exercise2.Order
import com.example.testkursignite.exercise2.OrderRepository

class FakeOrderRepository : OrderRepository {

    val orders = mutableMapOf<String, Order>()

    override fun findById(id: String): Order? = orders[id]

    override fun save(order: Order) {
        orders[order.id] = order
    }
}

