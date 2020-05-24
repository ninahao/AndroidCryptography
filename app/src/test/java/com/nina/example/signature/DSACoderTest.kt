package com.nina.example.signature

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DSACoderTest {
    lateinit var publicKey: ByteArray
    lateinit var privateKey: ByteArray

    @Before
    fun initKey() {
        val keyMap = DSACoder.initKey()
        publicKey = DSACoder.getPublicKey(keyMap)
        privateKey = DSACoder.getPrivateKey(keyMap)

        println("DSA public key: ${encodeBase64(publicKey)}")
        println("DSA private key: ${encodeBase64(privateKey)}")
    }

    @Test
    fun test() {
        val inputStr = "DSA Signature"
        val data = inputStr.toByteArray(Charsets.UTF_8)
        val sign = DSACoder.sign(data, privateKey)
        println("signature: ${encodeBase64(sign)}")
        val status = DSACoder.verify(data, publicKey, sign)
        println("status: $status")
        assertTrue(status)
    }

    private fun encodeBase64(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }
}