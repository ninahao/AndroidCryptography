package com.nina.example.signature

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RSACoderTest {
    lateinit var publicKey: ByteArray
    lateinit var privateKey: ByteArray

    @Before
    fun initKey() {
        val keyMap = RSACoder.initKey()
        publicKey = RSACoder.getPublicKey(keyMap)
        privateKey = RSACoder.getPrivateKey(keyMap)

        println("RSA public key: ${encodeBase64(publicKey)}")
        println("RSA private key: ${encodeBase64(privateKey)}")
    }

    @Test
    fun test() {
        val inputStr = "RSA Signature"
        val data = inputStr.toByteArray(Charsets.UTF_8)
        val sign = RSACoder.sign(data, privateKey)
        println("signature: ${encodeBase64(sign)}")
        val status = RSACoder.verify(data, publicKey, sign)
        println("status: $status")
        assertTrue(status)
    }

    private fun encodeBase64(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }
}