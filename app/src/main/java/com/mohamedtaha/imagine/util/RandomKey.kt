package com.mohamedtaha.imagine.util

import android.content.Context
import android.os.Build
import java.lang.StringBuilder
import java.security.SecureRandom
private val HEX_CHARS = "0123456789ABCDEF".toCharArray()
class RandomKey {
    private lateinit var rawByteKey:ByteArray
    private lateinit var dbCharKey:CharArray

    //Generate a new database key.
    fun createNewKey(){
        //This is the raw key that we will be encrypting + storing
        rawByteKey = generateRandomKey()
    //This is the key that will be used by room
        //dbCharKey = rawByteKey.toHex()
    }
    fun ByteArray.toHex():String{
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0xF0
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString()
    }
    fun generateRandomKey():ByteArray =
        ByteArray(32).apply { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            SecureRandom.getInstanceStrong().nextBytes(this)
        else
            SecureRandom().nextBytes(this) }
}














