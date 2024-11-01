package com.example

import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testGameFlow() = testApplication {
        application {
            configureRouting()
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("WELCOME TO THE GAME OF TWO TO TEN"))
        }

        client.post("/") {
            setBody(listOf("textInp" to "50").formUrlEncode())
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
        }.apply {
            assertEquals(HttpStatusCode.Found, status)
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("YOUR 'LUCKY LIMIT' CARD IS A"))
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("CARD #1 IS A"))
        }

        client.post("/") {
            setBody(listOf("textInp" to "Y").formUrlEncode())
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
        }.apply {
            assertEquals(HttpStatusCode.Found, status)
        }


        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("CARD #2 IS A"))
        }


        client.post("/") {
            setBody(listOf("textInp" to "N").formUrlEncode())
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
        }.apply {
            assertEquals(HttpStatusCode.Found, status)
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("YOU NOW HAVE $"))
        }

        client.post("/") {
            setBody(listOf("textInp" to "N").formUrlEncode())
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
        }.apply {
            assertEquals(HttpStatusCode.Found, status)
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("HOPE YOU HAD FUN"))
        }
    }
}