package com.example.testkursignite.exercise3

enum class OrderStatus { PLACED, SHIPPED, CANCELLED }

data class Order(val id: String, val customerEmail: String, val status: OrderStatus) {

    fun ship(): Order {
        check(status == OrderStatus.PLACED) { "Only placed orders can be shipped" }
        return copy(status = OrderStatus.SHIPPED)
    }
}

interface EmailSender {
    fun send(to: String, message: String)
}

class ShippingService(private val emailSender: EmailSender) {

    fun ship(order: Order): Order {
        val shipped = order.ship()
        emailSender.send(order.customerEmail, "Your order ${order.id} has been shipped")
        return shipped
    }
}

