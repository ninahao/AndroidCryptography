package com.nina.example.symmetric

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DESCoderTest {
    @Test
    fun test() {
        val inputStr = "DES"
        var inputData = inputStr.toByteArray(Charsets.UTF_8)
        println("plaintext: $inputStr")

        val key = DESCoder.initKey()
        println("security key: ${Base64.encodeBase64String(key)}")

        inputData = DESCoder.encrypt(inputData, key)
        println("cipher text: ${Base64.encodeBase64String(inputData)}")

        val outputData = DESCoder.decrypt(inputData, key)
        val outputStr = String(outputData)
        println("decrypted text: $outputStr")

        assertEquals(inputStr, outputStr)
    }
}