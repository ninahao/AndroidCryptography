package com.nina.example.symmetric

import org.apache.commons.codec.binary.Base64
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PBECoderTest {
    @Test
    fun test() {
        val inputStr = "PBE"
        println("plaintext: $inputStr")

        var inputData = inputStr.toByteArray(Charsets.UTF_8)
        val password = "passw0rd"
        println("password: $password")

        val salt = PBECoder.initSalt()
        println("salt: ${Base64.encodeBase64String(salt)}")

        inputData = PBECoder.encrypt(inputData, password, salt)
        println("cipher text: ${Base64.encodeBase64String(inputData)}")

        val outputData = PBECoder.decrypt(inputData, password, salt)
        val outputStr = String(outputData)
        println("decrypted text: $outputStr")

        assertEquals(inputStr, outputStr)
    }
}