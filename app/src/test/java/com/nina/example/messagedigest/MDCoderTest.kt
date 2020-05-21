package com.nina.example.messagedigest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MDCoderTest {
    @Test
    fun encodeMD2Test() {
        val str = "MD2 Message Digest"

        val data1 = MDCoder.encodeMD2(str.toByteArray())
        val data2 = MDCoder.encodeMD2(str.toByteArray())

        assertArrayEquals(data1, data2)
    }

    @Test
    fun encodeMD5Test() {
        val str = "MD5 Message Digest"

        val data1 = MDCoder.encodeMD5(str.toByteArray())
        val data2 = MDCoder.encodeMD5(str.toByteArray())

        assertArrayEquals(data1, data2)
    }
}