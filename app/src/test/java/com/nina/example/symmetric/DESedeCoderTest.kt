package com.nina.example.symmetric

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DESedeCoderTest {
    @Test
    fun test() {
        val inputStr = "DESede"
        var inputData = inputStr.toByteArray(Charsets.UTF_8)
        println("plaintext: $inputStr")

        val key = DESedeCoder.initKey()
        println("security key: ${Base64.encodeBase64String(key)}")

        inputData = DESedeCoder.encrypt(inputData, key)
        println("cipher text: ${Base64.encodeBase64String(inputData)}")

        val outputData = DESedeCoder.decrypt(inputData, key)
        val outputStr = String(outputData)
        println("decrypted text: $outputStr")

        assertEquals(inputStr, outputStr)
    }
}