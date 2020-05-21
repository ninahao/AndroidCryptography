package com.nina.example.messagedigest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CodecMD5CoderTest {
    @Test
    fun encodeMD5Test() {
        val str = "MD5 Message Digest"

        val data1 = CodecMD5Coder.encodeMD5(str)
        val data2 = CodecMD5Coder.encodeMD5(str)

        assertArrayEquals(data1, data2)
    }

    @Test
    fun encodeMD5HexTest() {
        val str = "MD5 HEX Message Digest"

        val data1 = CodecMD5Coder.encodeMD5Hex(str)
        val data2 = CodecMD5Coder.encodeMD5Hex(str)

        assertEquals(data1, data2)
    }
}