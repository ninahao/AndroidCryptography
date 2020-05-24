package com.nina.example.asymmetric

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ECDHCoderTest {
    //part A key
    lateinit var publicKey1: ByteArray
    lateinit var privateKey1: ByteArray
    lateinit var key1: ByteArray

    //part B key
    lateinit var publicKey2: ByteArray
    lateinit var privateKey2: ByteArray
    lateinit var key2: ByteArray

    @Before
    fun initKey() {
        val keyMap1 = ECDHCoder.initKey()
        publicKey1 = ECDHCoder.getPublicKey(keyMap1)
        privateKey1 = ECDHCoder.getPrivateKey(keyMap1)
        println("part A public key: ${encodeBase64(publicKey1)}")
        println("part A private key: ${encodeBase64(privateKey1)}")

        //generate local key pair by part A public key
        val keyMap2 = ECDHCoder.initKey(publicKey1)
        publicKey2 = ECDHCoder.getPublicKey(keyMap2)
        privateKey2 = ECDHCoder.getPrivateKey(keyMap2)
        println("part B public key: ${encodeBase64(publicKey2)}")
        println("part B private key: ${encodeBase64(privateKey2)}")

        key1 = ECDHCoder.getSecretKey(publicKey2, privateKey1)
        println("part A Local key: ${encodeBase64(key1)}")

        key2 = ECDHCoder.getSecretKey(publicKey1, privateKey2)
        println("part B Local key: ${encodeBase64(key2)}")

        assertArrayEquals(key1, key2)
    }

    @Test
    fun test() {
        println("part A send data to part B")

        val inputStr1 = "DH A2B"
        println("plaintext: $inputStr1")

        println("encrypt data by part A local secret key")
        val code1 = ECDHCoder.encrypt(inputStr1.toByteArray(Charsets.UTF_8), key1)
        println("cipher text: ${encodeBase64(code1)}")

        println("decrypt data by part B local secret key")
        val decode1 = ECDHCoder.decrypt(code1, key2)
        val output1 = String(decode1)
        println("decrypted text: $output1")

        assertEquals(inputStr1, output1)

        println("part B send data to part A")

        val inputStr2 = "DH B2A"
        println("plaintext: $inputStr2")

        println("encrypt data by part B local secret key")
        val code2 = ECDHCoder.encrypt(inputStr2.toByteArray(Charsets.UTF_8), key2)
        println("cipher text: ${encodeBase64(code2)}")

        println("decrypt data by part A local secret key")
        val decode2 = ECDHCoder.decrypt(code2, key1)
        val output2 = String(decode2)
        println("decrypted text: $output2")

        assertEquals(inputStr2, output2)
    }

    fun encodeBase64(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }

    fun decodeBase64(data: String): ByteArray {
        return Base64.decodeBase64(data)
    }
}