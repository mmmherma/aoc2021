package day8

import utils.FileUtils

class Day8 {
    val fileUtils = FileUtils()
    var output = ArrayList<String>()

    fun readProblem(fileName: String) {
        // Read file within lines
        val data = fileUtils.getFileFromResources(fileName)

        data.forEach{
            val outputTokens = it.split(" | ")[1].split(" ")

            outputTokens.forEach {
                output.add(it)
            }
        }
    }

    fun countUniqueNumbers(fileName: String): Int {
        readProblem(fileName)

        var count = 0

        output.forEach {
            when (it.length) {
                2, 4, 3, 7 -> count++
            }
        }

        return count
    }
}