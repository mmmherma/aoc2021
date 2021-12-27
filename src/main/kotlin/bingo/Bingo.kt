package bingo

import utils.FileUtils
import java.lang.Exception

class Bingo {
    private val fileUtils = FileUtils()
    var solution = ArrayList<Int>()
    var boards = ArrayList<Board>()

    fun readProblem(fileName: String) {
        // Read file within boards and numbers
        val data = fileUtils.getFileFromResources(fileName)

        // Read first line with exposed numbers
        val solutionTokens = data[0].split(",")
        solutionTokens.forEach {
            solution.add(it.toInt())
        }

        var board = ArrayList<Int>()
        for (i in 2 until data.size) {
            val tokens = data[i].split(" ")
            if (tokens.size < 2) {
                addBoard(board)
                board.clear()
            } else {
                val line = data[i].split("\\s+".toRegex())
                line.forEach {
                    try {
                        board.add(it.toInt())
                    } catch (e: Exception) {
                        println("Exception while storing number in board")
                    }
                }
            }
        }
        addBoard(board)

        boards.forEach {
            it.createMask()
        }
    }

    private fun addBoard(board: ArrayList<Int>) {
        val completeBoard = Board()
        completeBoard.board = board.clone() as ArrayList<Int>
        boards.add(completeBoard)
    }

    fun solveBingo(): Int {
        for (item in solution) {
            for (board in boards) {
                board.checkNumber(item)
                if (!board.win) {
                    if (board.checkBingo(item) > 0) {
                        return board.result
                    }
                }
            }
        }

        return -1
    }

    fun solveLastBingo(): Int {
        var lastBingo = 0

        for (item in solution) {
            for (board in boards) {
                board.checkNumber(item)
                if (!board.win) {
                    if (board.checkBingo(item) > 0) {
                        lastBingo = board.result
                    }
                }
            }
        }

        return lastBingo
    }
}