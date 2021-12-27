package rangefinder

import utils.FileUtils
import java.io.InputStream

class RangeFinder {
    val fileUtils: FileUtils = FileUtils()

    fun computeSlidingWindowMeasures(windowSize: Int, fileName: String): Int {
        // Check if measurement is larger than previous
        val lines: List<String> = fileUtils.getFileFromResources(fileName)

        // Window sum comparison
        var previous = Int.MAX_VALUE
        var counter = 0

        for (i in (windowSize/2)..(lines.size-1-windowSize/2)) {
            val sum: Int = lines[i-1].toInt() + lines[i].toInt() + lines[i+1].toInt()

            if (sum > previous) {
                counter++
            }
            previous = sum
        }

        return counter
    }
}