package movement

import utils.FileUtils

class Movement {
    private val fileUtils: FileUtils = FileUtils()

    private var horizontal = 0
    private var depth = 0
    private var aim = 0

    fun getHorizontalPosition(fileName: String): Int {
        val lines: List<String> = fileUtils.getFileFromResources(fileName)

        lines.forEach {
            if (it.contains("forward")) {
                horizontal += it.split(" ")[1].toInt()
                depth += aim * it.split(" ")[1].toInt()
            }
        }

        return horizontal
    }

    fun getFinalDepth(fileName: String): Int {
        val lines: List<String> = fileUtils.getFileFromResources(fileName)

        lines.forEach {
            if (it.contains("down")) {
                depth += it.split(" ")[1].toInt()
                aim += it.split(" ")[1].toInt()
            } else if (it.contains("up")) {
                depth -= it.split(" ")[1].toInt()
                aim -= it.split(" ")[1].toInt()
            }
        }

        return depth
    }

    fun computeUsingAim(fileName: String): Unit {
        val lines: List<String> = fileUtils.getFileFromResources(fileName)

        lines.forEach {
            val tokens = it.split(" ")

            when (tokens[0]) {
                "down" -> {
                    aim += it.split(" ")[1].toInt()
                }
                "up" -> {
                    aim -= it.split(" ")[1].toInt()
                }
                "forward" -> {
                    horizontal += it.split(" ")[1].toInt()
                    depth += aim * it.split(" ")[1].toInt()
                }
                else -> {
                    println("No compatible movement: ${tokens[0]}")
                }
            }
        }
    }

    fun getFinalMovementCost(): Int {
        return horizontal * depth
    }
}