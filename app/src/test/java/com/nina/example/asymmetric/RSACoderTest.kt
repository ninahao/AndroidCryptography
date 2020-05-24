package com.nina.example.asymmetric

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
        println("encrypt by private key, decrypt by public key")
        val inputStr1 = "RSA encrypt by private key, decrypt by public key"
        println("plaintext: $inputStr1")
        val data1 = inputStr1.toByteArray(Charsets.UTF_8)
        val enCodedData1 = RSACoder.encryptByPrivateKey(data1, privateKey)
        println("cipher text: ${encodeBase64(enCodedData1)}")
        val decodedData1 = RSACoder.decryptByPublicKey(enCodedData1, publicKey)
        val outputStr1 = String(decodedData1)
        println("decrypted text: $outputStr1")
        assertEquals(inputStr1, outputStr1)

        println("encrypt by public key, decrypt by private key")
        val inputStr2 = "RSA encrypt by public key, decrypt by private key"
        println("plaintext: $inputStr2")
        val data2 = inputStr2.toByteArray(Charsets.UTF_8)
        val enCodedData2 = RSACoder.encryptByPublicKey(data2, publicKey)
        println("cipher text: ${encodeBase64(enCodedData2)}")
        val decodedData2 = RSACoder.decryptByPrivateKey(enCodedData2, privateKey)
        val outputStr2 = String(decodedData2)
        println("decrypted text: $outputStr2")
        assertEquals(inputStr2, outputStr2)
    }

    private fun encodeBase64(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }
}