package com.nina.example.asymmetric

import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.SecretKey
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.DHParameterSpec
import javax.crypto.spec.SecretKeySpec

object DHCoder {
    private const val KEY_ALGORITHM = "DH"
    //local secret key algorithm
    private const val SECRET_ALGORITHM = "AES"
    //DH default size is 1024, it is a multiple of 64, 512 ~ 1024
    private const val KEY_SIZE = 512
    private const val PUBLIC_KEY = "DHPublicKey"
    private const val PRIVATE_KEY = "DHPrivateKey"

    private const val CIPHER_ALGORITHM = "AES/ECB/PKCS5Paddin"

    /**
     * init part A secret key
     */
    fun initKey(): Map<String, Key> {
        val keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM)
        keyPairGenerator.initialize(KEY_SIZE)
        val keyPair = keyPairGenerator.genKeyPair()
        val publicKey = keyPair.public as DHPublicKey
        val privateKey = keyPair.private as DHPrivateKey
        val keyMap = HashMap<String, Key>(2)
        keyMap[PUBLIC_KEY] = publicKey
        keyMap[PRIVATE_KEY] = privateKey
        return keyMap
    }

    /**
     * init part B secret key
     */
    fun initKey(key: ByteArray): Map<String, Key> {
        //parse part A public key
        val x509EncodedKeySpec = X509EncodedKeySpec(key)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val pubKey = keyFactory.generatePublic(x509EncodedKeySpec)

        //generate part B secret key by part A public key
        val dhParameterSpec: DHParameterSpec = (pubKey as DHPublicKey).params
        val keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.algorithm)
        keyPairGenerator.initialize(dhParameterSpec)
        //generate part B public, private key
        val keyPair = keyPairGenerator.genKeyPair()
        val publicKey = keyPair.public as DHPublicKey
        val privateKey = keyPair.private as DHPrivateKey
        val keyMap = HashMap<String, Key>(2)
        keyMap[PUBLIC_KEY] = publicKey
        keyMap[PRIVATE_KEY] = privateKey
        return keyMap
    }

    fun encrypt(data: ByteArray, key: ByteArray): ByteArray {
        //generate local secret key
        val secretKey = SecretKeySpec(key, SECRET_ALGORITHM)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher.doFinal(data)
    }

    fun decrypt(data: ByteArray, key: ByteArray): ByteArray {
        //generate local secret key
        val secretKey = SecretKeySpec(key, SECRET_ALGORITHM)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        return cipher.doFinal(data)
    }

    /**
     * build local secret key
     */
    fun getSecretKey(publicKey: ByteArray, privateKey: ByteArray): ByteArray {
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)

        val x509EncodedKeySpec = X509EncodedKeySpec(publicKey)
        val pubKey = keyFactory.generatePublic(x509EncodedKeySpec) as PublicKey

        val pkcS8EncodedKeySpec = PKCS8EncodedKeySpec(privateKey)
        val priKey = keyFactory.generatePrivate(pkcS8EncodedKeySpec) as PrivateKey

        val keyAgreement:KeyAgreement = KeyAgreement.getInstance(keyFactory.algorithm)
        keyAgreement.init(priKey)
        keyAgreement.doPhase(pubKey, true)

        //generate local secret key
        val secretKey:SecretKey = keyAgreement.generateSecret(SECRET_ALGORITHM)
        return secretKey.encoded
    }

    fun getPublicKey(keyMap: Map<String, Key>): ByteArray {
        return keyMap[PUBLIC_KEY]?.encoded ?: byteArrayOf()
    }

    fun getPrivateKey(keyMap: Map<String, Key>): ByteArray {
        return keyMap[PRIVATE_KEY]?.encoded ?: byteArrayOf()
    }
}