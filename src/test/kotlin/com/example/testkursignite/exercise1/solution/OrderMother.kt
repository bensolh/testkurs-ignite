package com.example.testkursignite.exercise1.solution

import com.example.testkursignite.exercise1.Address
import com.example.testkursignite.exercise1.Customer
import com.example.testkursignite.exercise1.Order
import com.example.testkursignite.exercise1.OrderLine
import com.example.testkursignite.exercise1.OrderStatus
import com.example.testkursignite.exercise1.Product

object OrderMother {

    fun order(
        customer: Customer = customer(),
        lines: List<OrderLine> = listOf(line()),
    ): Order = Order("order-1", customer, lines, OrderStatus.PLACED)

    fun customer(isMember: Boolean = false): Customer =
        Customer("customer-1", "Kari Nordmann", "kari@example.com", address(), isMember)

    fun line(price: Int = 100, quantity: Int = 1): OrderLine =
        OrderLine(Product("sku-1", "Clean Code", price), quantity)

    private fun address(): Address = Address("Storgata 1", "0155", "Oslo", "Norway")
}

