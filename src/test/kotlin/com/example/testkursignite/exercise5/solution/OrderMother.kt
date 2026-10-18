package com.example.testkursignite.exercise5.solution

import com.example.testkursignite.exercise5.Order
import java.time.LocalDate

object OrderMother {

    fun placedOrder(): Order = Order("order-1", "customer-1", 500)

    fun shippedOrder(): Order = placedOrder().ship()

    fun deliveredOrder(on: LocalDate): Order = shippedOrder().deliver(on)

    fun cancelledOrder(): Order = placedOrder().cancel()
}

