package com.example.testkursignite.exercise1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun `Shipping is charged when subtotal is below the limit`() {
        val address = Address("Storgata 1", "0155", "Oslo", "Norway")
        val customer = Customer("customer-1", "Kari Nordmann", "kari@example.com", address, false)
        val book = Product("sku-1", "Clean Code", 349)
        val mug = Product("sku-2", "Coffee mug", 99)
        val order = Order(
            "order-1",
            customer,
            listOf(OrderLine(book, 1), OrderLine(mug, 1)),
            OrderStatus.PLACED,
        )

        assertEquals(79, order.shippingCost())
    }

    @Test
    fun `Shipping is free when subtotal is above the limit`() {
        val address = Address("Kongens gate 10", "7011", "Trondheim", "Norway")
        val customer = Customer("customer-2", "Ola Nordmann", "ola@example.com", address, false)
        val book = Product("sku-1", "Clean Code", 349)
        val mug = Product("sku-2", "Coffee mug", 99)
        val order = Order(
            "order-2",
            customer,
            listOf(OrderLine(book, 1), OrderLine(mug, 2)),
            OrderStatus.PLACED,
        )

        assertEquals(0, order.shippingCost())
    }

    @Test
    fun `Shipping is free for members`() {
        val address = Address("Bryggen 5", "5003", "Bergen", "Norway")
        val customer = Customer("customer-3", "Per Hansen", "per@example.com", address, true)
        val mug = Product("sku-2", "Coffee mug", 99)
        val order = Order(
            "order-3",
            customer,
            listOf(OrderLine(mug, 1)),
            OrderStatus.SHIPPED,
        )

        assertEquals(0, order.shippingCost())
    }

    @Test
    fun `Total is the sum of all lines plus shipping`() {
        val address = Address("Storgata 1", "0155", "Oslo", "Norway")
        val customer = Customer("customer-1", "Kari Nordmann", "kari@example.com", address, false)
        val book = Product("sku-1", "Clean Code", 349)
        val mug = Product("sku-2", "Coffee mug", 99)
        val order = Order(
            "order-1",
            customer,
            listOf(OrderLine(book, 1), OrderLine(mug, 1)),
            OrderStatus.PLACED,
        )

        assertEquals(527, order.total())
    }
}

