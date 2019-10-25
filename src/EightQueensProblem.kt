import java.util.concurrent.ThreadLocalRandom

class EightQueensProblem {

    companion object {
        var maxRuns = 100

        @JvmStatic
        fun main(args: Array<String>) {
            val experiment = EightQueensProblem()

                experiment.eightQueensWKQueens()
        }
    }

     fun printSolution(board: Array<IntArray>) {
        println("solved")
        for (i in 0 until 8) {
            for (j in 0 until 8)
                print(
                    "" + board[i][j]
                            + " "
                )
            println()
        }
    }

     fun isSafe(board: Array<IntArray>, row: Int, col: Int, k: Int): Boolean {
        var mColumn: Int
        var mRow: Int

        /* clear left side */
        mColumn = 0
        while (mColumn < col) {
            if (board[row][mColumn] >0)
                return false
            mColumn++
        }

        /* clear upper left diagonal */
        mColumn = col
        mRow = row
        while (mColumn >= 0 && mRow >= 0) {  //?mrow j>0
            if (board[mRow][mColumn] >0)
                return false
            mColumn--
            mRow--
        }

        /* clear lower left diagonal */
        mColumn = col
        mRow = row
        while ((mColumn > 0 && mRow <8) && (mRow >= 0 && mColumn < 8 )) {
                if (board[mRow][mColumn] > 0)
                    return false
                if (mColumn >= 0)
                    mColumn--
                if (mRow <= 7)
                    mRow++

        }

        return true
    }

     fun stepThroughBoard(board: Array<IntArray>, col: Int, k: Int): Boolean {

        if (col >= 8)
            return true

        for (i in 0 until 8) {

            if (isSafe(board, i, col, k)) {
                board[i][col] = 1

                if (stepThroughBoard(board, col + 1, k) == true)
                    return true

                board[i][col] = 0
            }
        }

        return false
    }

    private fun assignRandomQueensToBoard(k: Int, board: Array<IntArray>) {
            for(i in 0..k-1) {
                    val column = i
                    val row = ThreadLocalRandom.current().nextInt(0, 8)

                    if (isSafe(board, row, column, k)) {
                        board[row][column] = 2
                    } else {
                        board[row][column] = 0
                        println("failed to randomly create good choices")
                        eightQueensWKQueens()
                    }
                    //println("Position ${row},${column} ,is the position of the random queens")

            }
    }

     fun eightQueensWKQueens(): Boolean {
        val board =
            arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
            )
        var k =4

        assignRandomQueensToBoard(k, board)
        if (stepThroughBoard(board, 0+k, k) == false) {
            println("Solution does not exist")
            maxRuns--
            return false
        }

        printSolution(board)
        return true
    }
}