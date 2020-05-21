package com.nina.example.messagedigest

import java.security.MessageDigest

object MDCoder {
    /**
     * @param data data to be processed
     * @return ByteArray message digest
     */
    fun encodeMD2(data: ByteArray): ByteArray {
        val messageDigest = MessageDigest.getInstance("MD2")
        return messageDigest.digest(data)
    }

    /**
     * @param data data to be processed
     * @return ByteArray message digest
     */
    fun encodeMD5(data: ByteArray): ByteArray {
        val messageDigest = MessageDigest.getInstance("MD5")
        return messageDigest.digest()
    }
}