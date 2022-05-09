package name.tachenov.leetcode

class CheckIfThereIsAValidParenthesesStringPath {

    fun hasValidPath(grid: Array<CharArray>): Boolean {
        val hasValidPath = Array(grid.m) { Array(grid.n) { HashMap<Int, Boolean>() } }
        return hasValidPath(grid, hasValidPath, 0, 0, 0)
    }

    private fun hasValidPath(
        grid: Array<CharArray>,
        hasValidPath: Array<Array<java.util.HashMap<Int, Boolean>>>,
        i: Int,
        j: Int,
        sum: Int
    ): Boolean {
        hasValidPath[i][j][sum]?.let { return it }
        val newSum = sum + grid[i][j].value
        val result = if (i == grid.m - 1 && j == grid.n - 1) {
            newSum == 0
        } else if (newSum < 0) {
            false
        } else {
            val hasValidPathDown = if (i < grid.m - 1) hasValidPath(grid, hasValidPath, i + 1, j, newSum) else false
            val hasValidPathRight = if (j < grid.n - 1) hasValidPath(grid, hasValidPath, i, j + 1, newSum) else false
            hasValidPathDown || hasValidPathRight
        }
        hasValidPath[i][j][sum] = result
        return result
    }

    private val Array<CharArray>.m: Int get() = size

    private val Array<CharArray>.n: Int get() = this[0].size

    private val Char.value: Int get() = if (this == '(') +1 else -1

}
