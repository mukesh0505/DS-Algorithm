/*
Find the number of islands

Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island.
For example, the below matrix contains 5 islands

Input : mat[][] =   {{1, 1, 0, 0, 0},
                    {0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0},
                    {1, 0, 1, 0, 1}}
Output : 5
*/

class Island {

    var islandCount = 0

    private fun isSafeToVisit(matrix: Array<IntArray>, m: Int, n: Int, visited: Array<BooleanArray>): Boolean {
        val rowCount = matrix.size
        val colCount = matrix[0].size
        return m in 0 until rowCount && n in 0 until colCount && matrix[m][n] == 1 && visited[m][n].not()
    }

    private fun travelAll(matrix: Array<IntArray>, m: Int, n: Int, visited: Array<BooleanArray>) {
        val rowHelper = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val colHelper = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        visited[m][n] = true

        for (i in 0 until 8) {
            if(isSafeToVisit(matrix, m + rowHelper[i], n + colHelper[i], visited)) {
                travelAll(matrix, m + rowHelper[i], n + colHelper[i], visited)
            }
        }
    }

    fun countIsland(matrix: Array<IntArray>): Int {
        val rowCount = matrix.size
        val colCount = matrix[0].size
        val visited = Array(rowCount) {
            BooleanArray(colCount) {false}
        }
        for(i in matrix.indices) {
            for(j in matrix[i].indices) {
                if(matrix[i][j] == 1 && visited[i][j].not()) {
                    travelAll(matrix, i, j, visited)
                    islandCount++
                }
            }
        }
        return islandCount
    }
}


fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 1, 0, 0, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(1, 0, 1, 0, 1)
    )
    val island = Island()
    println(island.countIsland(matrix))
}
