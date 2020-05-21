package com.nina.example.messagedigest

import org.apache.commons.codec.digest.DigestUtils

object CodecMD5Coder {
    fun encodeMD5(data: String): ByteArray {
        return DigestUtils.md5(data)
    }

    fun encodeMD5Hex(data: String): String {
        return DigestUtils.md5Hex(data)
    }
}