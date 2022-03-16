package name.tachenov.leetcode

object TestData {

    @JvmStatic
    fun load(resource: String): Array<Array<Any>> = javaClass.getResourceAsStream(resource)?.bufferedReader()?.use { reader ->
        reader.readText().split("\n\n")
            .filter { it.isNotBlank() }
            .map { block ->
                block.split("\n")
                    .filter { it.isNotBlank() }
                    .map { line -> parse(line) }
                    .toTypedArray()
            }.toTypedArray()
    }!!

    private fun parse(line: String): Any = when {
        line.isInt() -> line.toInt()
        line.isLong() -> line.substring(0 until line.length - 1).toLong()
        line.isIntArray2D() -> line.toIntArray2D()
        else -> throw IllegalArgumentException("What is '$this'?")
    }

}

private fun String.isInt(): Boolean = all { it.isDigit() }

private fun String.isLong(): Boolean = endsWith('L') && substring(0 until length - 1).all { it.isDigit() }

private fun String.isIntArray2D(): Boolean = startsWith("[[") && all { it.isDigit() || it == '[' || it == ']' || it == ',' }

private fun String.toIntArray2D(): Array<IntArray> =
    substring(2 until length - 2)
        .split("],[")
        .map { array -> array.split(',').map { number -> number.toInt() }.toIntArray() }
        .toTypedArray()
