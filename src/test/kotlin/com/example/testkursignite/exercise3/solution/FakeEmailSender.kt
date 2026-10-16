package com.example.testkursignite.exercise3.solution

import com.example.testkursignite.exercise3.EmailSender

data class SentEmail(val to: String, val message: String)

class FakeEmailSender : EmailSender {

    val sentEmails = mutableListOf<SentEmail>()

    override fun send(to: String, message: String) {
        sentEmails.add(SentEmail(to, message))
    }
}

