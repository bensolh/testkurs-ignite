package com.example.testkursignite.exercise5

import java.time.LocalDate

object OrderMother {

    fun order(
        status: OrderStatus = OrderStatus.PLACED,
        deliveredOn: LocalDate? = null,
    ): Order = Order("order-1", "customer-1", 500, status, deliveredOn)
}

