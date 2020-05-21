package com.nina.example.messagedigest

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest

@RunWith(JUnit4::class)
class FileMD5Test {
    @Test
    fun testByMessageDigest() {
        val stream: InputStream? = FileMD5Test::class.java.getResourceAsStream("/mixed.txt")
        val digestInputStream = DigestInputStream(stream, MessageDigest.getInstance("MD5"))
        val buf = 1024
        val buffer = ByteArray(buf)
        var read = digestInputStream.read(buffer, 0, buf)
        while (read > -1) {
            read = digestInputStream.read(buffer, 0, buf)
        }
        digestInputStream.close()

        val messageDigest = digestInputStream.messageDigest
        val b = messageDigest.digest()

        val md5hex = Hex.encodeHexString(b)
        println(md5hex)

        assertEquals(md5hex, "e49a6e5076cf151a437af3abade7b655")
    }

    @Test
    fun testByDigestUtils() {
        val stream: InputStream? = FileMD5Test::class.java.getResourceAsStream("/mixed.txt")
        val md5hex = DigestUtils.md5Hex(stream)
        stream!!.close()
        assertEquals(md5hex, "e49a6e5076cf151a437af3abade7b655")
    }
}