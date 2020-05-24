package com.nina.example.signature

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ECDSACoderTest {
    lateinit var publicKey: ByteArray
    lateinit var privateKey: ByteArray

    @Before
    fun initKey() {
        val keyMap = ECDSACoder.initKey()
        publicKey = ECDSACoder.getPublicKey(keyMap)
        privateKey = ECDSACoder.getPrivateKey(keyMap)

        println("ECDSA public key: ${encodeBase64(publicKey)}")
        println("ECDSA private key: ${encodeBase64(privateKey)}")
    }

    @Test
    fun test() {
        val inputStr = "ECDSA Signature"
        val data = inputStr.toByteArray(Charsets.UTF_8)
        val sign = ECDSACoder.sign(data, privateKey)
        println("signature: ${encodeBase64(sign)}")
        val status = ECDSACoder.verify(data, publicKey, sign)
        println("status: $status")
        assertTrue(status)
    }

    private fun encodeBase64(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }
}