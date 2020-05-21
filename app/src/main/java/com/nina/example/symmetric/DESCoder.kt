package com.nina.example.symmetric

import java.security.Key
import java.security.KeyFactory
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec

object DESCoder {
    const val KEY_ALGORITHM = "DES"
    const val KEY_SIZE = 56
    const val CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding"

    fun toKey(key: ByteArray): Key {
        val dks = DESKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM)
        val secretKey = keyFactory.generateSecret(dks)
        return secretKey
    }

    fun decrypt(data: ByteArray, key: ByteArray): ByteArray {
        val k = toKey(key)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, k)
        return cipher.doFinal(data)
    }

    fun encrypt(data: ByteArray, key: ByteArray): ByteArray {
        val k = toKey(key)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, k)
        return cipher.doFinal(data)
    }

    fun initKey(): ByteArray {
        val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM)
        keyGenerator.init(KEY_SIZE)
        val secretKey = keyGenerator.generateKey()
        return secretKey.encoded
    }
}