package com.nina.example.symmetric

import java.security.Key
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.PBEParameterSpec

object PBECoder {
    const val ALGORITHM = "PBEWITHMD5andDES"
    const val ITERATION_COUNT = 100

    fun initSalt(): ByteArray {
        val secureRandom = SecureRandom()
        return secureRandom.generateSeed(8)
    }

    fun toKey(password: String): Key {
        val keySpec = PBEKeySpec(password.toCharArray())
        val secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        return secretKeyFactory.generateSecret(keySpec)
    }

    fun encrypt(data: ByteArray, password: String, salt: ByteArray): ByteArray {
        val key = toKey(password)
        val pbeParameterSpec = PBEParameterSpec(salt, ITERATION_COUNT)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec)
        return cipher.doFinal(data)
    }

    fun decrypt(data: ByteArray, password: String, salt: ByteArray): ByteArray {
        val key = toKey(password)
        val pbeParameterSpec = PBEParameterSpec(salt, ITERATION_COUNT)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec)
        return cipher.doFinal(data)
    }
}