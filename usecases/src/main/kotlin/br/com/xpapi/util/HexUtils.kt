package br.com.xpapi.util

class HexUtils {
    private val encoding = "UTF-8"

    override fun toString(): String {
        return super.toString() + "[charsetName=" + this.encoding + "]"
    }

    companion object {
        fun encodeArrayOfChar(paramArrayOfChar: CharArray): ByteArray {
            val k = paramArrayOfChar.size
            if (k and 0x1 != 0) {
                throw Exception("Odd number of characters.")
            }
            val arrayOfByte = ByteArray(k shr 1)
            var i = 0
            var j = 0
            while (j < k) {
                val m = getCharacterNumericValue(paramArrayOfChar[j], j)
                j += 1
                val n = getCharacterNumericValue(paramArrayOfChar[j], j)
                j += 1
                arrayOfByte[i] = (m shl 4 or n and 0xFF).toByte()
                i += 1
            }
            return arrayOfByte
        }

        fun shiftByteArray(arrayOfBytes: ByteArray): String {
            val localStringBuilder = StringBuilder()
            val j = arrayOfBytes.size
            var i = 0
            while (i < j) {
                val k = arrayOfBytes[i].toInt()
                val m = k shr 4 and 0xF shl 4
                if (m == 0) {
                    localStringBuilder.append('0')
                }
                localStringBuilder.append(Integer.toHexString(m or (k and 0xF)))
                i += 1
            }
            return localStringBuilder.toString()
        }

        private fun getCharacterNumericValue(paramChar: Char, paramInt: Int): Int {
            val i = Character.digit(paramChar, 16)
            if (i == -1) {
                throw Exception("Illegal hexadecimal character $paramChar at index $paramInt")
            }
            return i
        }
    }
}