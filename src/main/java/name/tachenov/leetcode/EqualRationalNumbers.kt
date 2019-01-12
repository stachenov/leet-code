package name.tachenov.leetcode

class EqualRationalNumbers {
    fun isRationalEqual(S: String, T: String): Boolean {
        return RationalNumber.parse(S) == RationalNumber.parse(T)
    }
}

data class RationalNumber(val numerator: Long, val denominator: Long) {
    companion object {
        @JvmStatic
        fun parse(s: String): RationalNumber {
            val decimal = s.indexOf('.')
            val open = s.indexOf('(')
            val close = s.indexOf(')')
            val intPart = (if (decimal == -1) s else s.substring(0, decimal)).toLong()
            val nonRepeatingPart = when {
                decimal == -1 -> ""
                open == -1 -> s.substring(decimal + 1)
                else -> s.substring(decimal + 1, open)
            }
            val repeatingPart = if (open == -1)
                ""
            else
                s.substring(open + 1, close)
            var denominator = powOfTen(nonRepeatingPart.length)
            var numerator = intPart * denominator
            if (!nonRepeatingPart.isEmpty()) {
                numerator += nonRepeatingPart.toInt()
            }
            if (!repeatingPart.isEmpty()) {
                denominator *= nines(repeatingPart.length)
                numerator *= nines(repeatingPart.length)
                numerator += repeatingPart.toLong()
            }
            return RationalNumber(numerator, denominator)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RationalNumber

        val normalized = normalized()
        val otherNormalized = other.normalized()

        if (normalized.numerator != otherNormalized.numerator) return false
        if (normalized.denominator != otherNormalized.denominator) return false

        return true
    }

    private fun normalized(): RationalNumber {
        val gcd = gcd(numerator, denominator)
        return RationalNumber(numerator / gcd, denominator / gcd)
    }

    override fun hashCode(): Int {
        val normalized = normalized()
        var result = normalized.numerator.hashCode()
        result = 31 * result + normalized.denominator.hashCode()
        return result
    }
}

private fun powOfTen(pow: Int): Long {
    var result = 1L
    for (i in 1..pow)
        result *= 10
    return result
}

private fun nines(nines: Int): Long {
    var result = 0L
    for (i in 1..nines) {
        result *= 10
        result += 9
    }
    return result
}

private fun gcd(numerator: Long, denominator: Long): Long {
    return if (numerator == 0L) denominator else gcd(denominator % numerator, numerator)
}
