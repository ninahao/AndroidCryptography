package com.nina.example.messagedigest

import javax.crypto.KeyGenerator
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object MACCoder {
    fun initHmacMD5Key(): ByteArray {
        val keyGenerator = KeyGenerator.getInstance("HmacMD5")
        val secretKey = keyGenerator.generateKey()
        return secretKey.encoded
    }

    fun encodeHmacMD5(data: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, "HmacMD5")
        val mac = Mac.getInstance(secretKey.algorithm)
        mac.init(secretKey)
        return mac.doFinal(data)
    }

    fun initHmacSHA512Key(): ByteArray {
        val keyGenerator = KeyGenerator.getInstance("HmacSHA512")
        val secretKey = keyGenerator.generateKey()
        return secretKey.encoded
    }

    fun encodeHmacSHA512(data: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, "HmacSHA512")
        val mac = Mac.getInstance(secretKey.algorithm)
        mac.init(secretKey)
        return mac.doFinal(data)
    }
}