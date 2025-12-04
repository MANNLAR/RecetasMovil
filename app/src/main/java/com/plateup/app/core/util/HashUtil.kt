package com.plateup.app.core.util

import java.security.MessageDigest

object HashUtil {
    fun sha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(input.toByteArray())
        return bytes.joinToString(separator = "") { byte ->
            val hex = (byte.toInt() and 0xff).toString(16)
            if (hex.length == 1) "0$hex" else hex
        }
    }
}
