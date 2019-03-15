package name.tachenov.leetcode

class BinaryWatch {
    fun readBinaryWatch(num: Int): List<String> {
        val result = ArrayList<String>()
        readBinaryWatch(result, 0, num, 0)
        return result
    }

    private fun readBinaryWatch(result: ArrayList<String>, code: Int, num: Int, start: Int) {
        if (num == 0) {
            if (Time(code).isValid)
                result += Time(code).toString()
            return
        }
        for (i in start until TOTAL_BITS) {
            readBinaryWatch(result, code or (1 shl i), num - 1, i + 1)
        }
    }

    companion object {
        private const val HOUR_BITS = 4
        private const val MINUTE_BITS = 6
        private const val TOTAL_BITS = HOUR_BITS + MINUTE_BITS
    }

    private data class Time(val hour: Int, val minute: Int) {
        constructor(code: Int) : this(code shr MINUTE_BITS, code and 0x3f)

        override fun toString() = String.format("%d:%02d", hour, minute)

        val isValid get() = hour in 0..11 && minute in 0..59
    }
}
