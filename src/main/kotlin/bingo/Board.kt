package bingo

class Board(dimension: Int = 5) {
    var board = ArrayList<Int>()
    var mask = ArrayList<Int>()
    var result = 0
    var win = false

    fun createMask() {
        for (i in 0 until board.size) {
            mask.add(0)
        }
    }

    private fun computeBoardValue(): Int {
        var boardValue = 0

        for (i in 0 until mask.size) {
            if (mask[i] == 0) {
                boardValue += board[i]
            }
        }

        return boardValue
    }

    fun checkNumber(item: Int) {
        val index = board.indexOf(item)

        if (index != -1) {
            mask[index] = 1
        }
    }

    fun checkBingo(item: Int): Int {
        var line = true

        // Check horizontal lines
        for (i in 0 until mask.size step 5) {
            line = true
            var lineValue = 0

            // Check horizontal lines
            for (j in 0 until 5) {
                lineValue += board[i+j]
                if (mask[i+j] == 0) {
                    line = false
                }
            }
            if (line) {
                result = item * computeBoardValue()
                win = true
//                return result
            }
        }

        // Check vertical line
        for (i in 0 until 5) {
            line = true
            var lineValue = 0

            for (j in 0 until 5) {
                lineValue += board[i+(5*j)]
                if (mask[i+(5*j)] == 0) {
                    line = false
                }
            }
            if (line) {
                result = item * computeBoardValue()
                win = true
//                return result
            }
        }

        return if (win) result else 0
    }
}