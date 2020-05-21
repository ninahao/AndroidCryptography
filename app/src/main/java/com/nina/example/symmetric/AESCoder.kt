package com.nina.example.symmetric

import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

object AESCoder {
    const val KEY_ALGORITHM = "AES"
    const val KEY_SIZE = 256 //128, 192
    const val CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"

    fun toKey(key: ByteArray): Key {
        return SecretKeySpec(key, KEY_ALGORITHM)
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