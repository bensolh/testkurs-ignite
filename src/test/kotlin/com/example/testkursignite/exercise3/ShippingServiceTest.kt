package com.example.testkursignite.exercise3

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class ShippingServiceTest {

    private val emailSender = mock(EmailSender::class.java)
    private val service = ShippingService(emailSender)

    @Test
    fun `ship order`() {
        val order = Order("order-1", "kari@example.com", OrderStatus.PLACED)

        service.ship(order)

        verify(emailSender, times(1)).send("kari@example.com", "Your order order-1 has been shipped")
        verifyNoMoreInteractions(emailSender)
    }

    @Test
    fun `ship cancelled order`() {
        val order = Order("order-1", "kari@example.com", OrderStatus.CANCELLED)

        assertThatThrownBy { service.ship(order) }
            .isInstanceOf(IllegalStateException::class.java)

        verify(emailSender, never()).send(anyString(), anyString())
    }
}

