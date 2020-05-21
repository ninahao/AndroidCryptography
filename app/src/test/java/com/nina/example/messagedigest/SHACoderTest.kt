package com.nina.example.messagedigest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SHACoderTest {
    @Test
    fun encodeSHATest() {
        val str = "SHA1 Message Digest"

        val data1 = SHACoder.encodeSHA(str.toByteArray())
        val data2 = SHACoder.encodeSHA(str.toByteArray())

        assertArrayEquals(data1, data2)
    }

    @Test
    fun encodeSHA256Test() {
        val str = "SHA256 Message Digest"

        val data1 = SHACoder.encodeSHA256(str.toByteArray())
        val data2 = SHACoder.encodeSHA256(str.toByteArray())

        assertArrayEquals(data1, data2)
    }

    @Test
    fun encode384SHATest() {
        val str = "SHA384 Message Digest"

        val data1 = SHACoder.encodeSHA384(str.toByteArray())
        val data2 = SHACoder.encodeSHA384(str.toByteArray())

        assertArrayEquals(data1, data2)
    }

    @Test
    fun encodeSHA512Test() {
        val str = "SHA512 Message Digest"

        val data1 = SHACoder.encodeSHA512(str.toByteArray())
        val data2 = SHACoder.encodeSHA512(str.toByteArray())

        assertArrayEquals(data1, data2)
    }
}