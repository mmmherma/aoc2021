package map

import utils.FileUtils
import kotlin.math.sign

class HydrothermalMap {
    private val fileUtils = FileUtils()
    var map = mutableMapOf<Point, Int>()

    fun readMap(fileName: String) {
        // Read file within lines
        val data = fileUtils.getFileFromResources(fileName)

        // Read all segments
        data.forEach {
            val tokens = it.split(" ")

            // Get first point of segment
            var point = tokens[0].split(",")
            val point1: Point = Point(point[0].toInt(), point[1].toInt())
            // Get second point of segment
            point = tokens[2].split(",")
            val point2: Point = Point(point[0].toInt(), point[1].toInt())

            // Get only horizontal and vertical segments
            if ((point1.x == point2.x) || (point1.y == point2.y)) {
                // Store vertices
                if (map.containsKey(point1)) {
                    map[point1] = map[point1]!!.inc()
                } else {
                    map[point1] = 1
                }
                if (map.containsKey(point2)) {
                    map[point2] = map[point2]!!.inc()
                } else {
                    map[point2] = 1
                }

                // Store intermediate points
                var xInit = point1.x
                var xEnd = point2.x
                var yInit = point1.y
                var yEnd = point2.y

                if (point1.x > point2.x) {
                    xInit = point2.x
                    xEnd = point1.x
                }
                if ((point1.y > point2.y)) {
                    yInit = point2.y
                    yEnd = point1.y
                }

                if (point1.x == point2.x) {
                    for (i in yInit+1 until yEnd) {
                        val point = Point(point1.x, i)
                        if (map.containsKey(point)) {
                            map[point] = map[point]!!.inc()
                        } else {
                            map[point] = 1
                        }
                    }
                } else if (point1.y == point2.y) {
                    for (i in xInit+1 until xEnd) {
                        val point = Point(i, point1.y)
                        if (map.containsKey(point)) {
                            map[point] = map[point]!!.inc()
                        } else {
                            map[point] = 1
                        }
                    }
                }
            } else {
                // Store intermediate points
                var xInit = point1.x
                var xEnd = point2.x
                var yInit = point1.y
                var yEnd = point2.y
                val incX = point2.x - point1.x
                val incY = point2.y - point1.y

                if (point1.x > point2.x) {
                    xInit = point2.x
                    xEnd = point1.x
                }
                if ((point1.y > point2.y)) {
                    yInit = point2.y
                    yEnd = point1.y
                }

                for (i in 0 .. (xEnd-xInit)) {
                    val point = Point(point1.x+(i*sign(incX.toFloat()).toInt()), point1.y+(i*sign(incY.toFloat()).toInt()))
                    if (map.containsKey(point)) {
                        map[point] = map[point]!!.inc()
                    } else {
                        map[point] = 1
                    }
                }
            }
        }
    }

    fun getOverlap(): Int {
        return map.filter {
            (key, value) -> value > 1
        }.size
    }
}