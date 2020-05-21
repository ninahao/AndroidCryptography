package com.nina.example.messagedigest

import java.security.MessageDigest

object SHACoder {
    fun encodeSHA(data: ByteArray): ByteArray {
        val md = MessageDigest.getInstance("SHA")
        return md.digest(data)
    }

    fun encodeSHA256(data: ByteArray): ByteArray {
        val md = MessageDigest.getInstance("SHA-256")
        return md.digest(data)
    }

    fun encodeSHA384(data: ByteArray): ByteArray {
        val md = MessageDigest.getInstance("SHA-384")
        return md.digest(data)
    }

    fun encodeSHA512(data: ByteArray): ByteArray {
        val md = MessageDigest.getInstance("SHA-512")
        return md.digest(data)
    }
}