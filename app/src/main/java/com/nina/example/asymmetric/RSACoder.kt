package com.nina.example.asymmetric

import java.security.Key
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RSACoder {
    private const val KEY_ALGORITHM = "RSA"
    //default 1024, is multiple of 64, 512 ~ 65536
    private const val KEY_SIZE = 512
    private const val CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding"

    private const val PUBLIC_KEY = "RSAPublicKey"
    private const val PRIVATE_KEY = "RSAPrivateKey"

    fun decryptByPrivateKey(data: ByteArray, key: ByteArray): ByteArray {
        val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(key)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val privateKey = keyFactory.generatePrivate(pkcS8EncodedKeySpec)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }

    fun decryptByPublicKey(data: ByteArray, key: ByteArray): ByteArray {
        val x509EncodedKeySpec = X509EncodedKeySpec(key)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val publicKey = keyFactory.generatePublic(x509EncodedKeySpec)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, publicKey)
        return cipher.doFinal(data)

    }

    fun encryptByPublicKey(data: ByteArray, key: ByteArray): ByteArray {
        val x509EncodedKeySpec = X509EncodedKeySpec(key)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val publicKey = keyFactory.generatePublic(x509EncodedKeySpec)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data)

    }

    fun encryptByPrivateKey(data: ByteArray, key: ByteArray): ByteArray {
        val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(key)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val privateKey = keyFactory.generatePrivate(pkcS8EncodedKeySpec)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }

    fun getPublicKey(map: Map<String, Key>): ByteArray {
        val key = map[PUBLIC_KEY]
        return key?.encoded ?: byteArrayOf()
    }

    fun getPrivateKey(map: Map<String, Key>): ByteArray {
        val key = map[PRIVATE_KEY]
        return key?.encoded ?: byteArrayOf()
    }

    fun initKey(): Map<String, Key> {
        val keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM)
        keyPairGenerator.initialize(KEY_SIZE)
        val keyPair = keyPairGenerator.generateKeyPair()
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey
        val map = HashMap<String, Key>(2)
        map[PUBLIC_KEY] = publicKey
        map[PRIVATE_KEY] = privateKey
        return map
    }
}