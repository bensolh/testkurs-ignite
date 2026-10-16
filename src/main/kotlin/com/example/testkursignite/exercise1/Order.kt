package com.example.testkursignite.exercise1

data class Address(val street: String, val postalCode: String, val city: String, val country: String)

data class Customer(val id: String, val name: String, val email: String, val address: Address, val isMember: Boolean)

data class Product(val sku: String, val name: String, val price: Int)

data class OrderLine(val product: Product, val quantity: Int) {
    fun total(): Int = product.price * quantity
}

enum class OrderStatus { PLACED, SHIPPED, CANCELLED }

data class Order(val id: String, val customer: Customer, val lines: List<OrderLine>, val status: OrderStatus) {

    fun subtotal(): Int = lines.sumOf { it.total() }

    fun shippingCost(): Int =
        if (customer.isMember || subtotal() >= FREE_SHIPPING_LIMIT) 0 else SHIPPING_FEE

    fun total(): Int = subtotal() + shippingCost()

    companion object {
        const val FREE_SHIPPING_LIMIT = 500
        const val SHIPPING_FEE = 79
    }
}

