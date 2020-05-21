package com.nina.example.messagedigest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MACCoderTest {
    @Test
    fun hmacMD5Test() {
        val str = "HMACMD5 Message Digest"

        val key = MACCoder.initHmacMD5Key()
        val data1 = MACCoder.encodeHmacMD5(str.toByteArray(), key)
        val data2 = MACCoder.encodeHmacMD5(str.toByteArray(), key)

        assertArrayEquals(data1, data2)
    }

    @Test
    fun hmacSHA512Test() {
        val str = "HMACSHA512 Message Digest"

        val key = MACCoder.initHmacMD5Key()
        val data1 = MACCoder.encodeHmacSHA512(str.toByteArray(), key)
        val data2 = MACCoder.encodeHmacSHA512(str.toByteArray(), key)

        assertArrayEquals(data1, data2)
    }
}