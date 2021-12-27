package crab

import utils.FileUtils
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min

class Crab {
    private val fileUtils = FileUtils()
    var crabs = ArrayList<Int>()

    fun readProblem(fileName: String) {
        // Read file within lines
        val data = fileUtils.getFileFromResources(fileName)

        val tokens = data[0].split(",")

        tokens.forEach {
            crabs.add(it.toInt())
        }
    }

    private fun computeMedian(): Int {
        val sortedCrabs = crabs.sorted()

        return if (sortedCrabs.size % 2 == 0) {
            sortedCrabs[sortedCrabs.size/2]
        } else {
            (sortedCrabs[(sortedCrabs.size-1)/2] + sortedCrabs[(sortedCrabs.size+1)/2])/2
        }
    }

    private fun computeMean(): Double {
        return crabs.sum() / crabs.size.toDouble()
    }

    private fun getLinealFuel(value: Int): Int {
        var fuel = 0
        println(value)

        crabs.forEach {
            fuel += abs(value - it)
        }

        return fuel
    }

    private fun gauss(num: Int): Double {
        return num.toDouble() * ((num.toDouble()+1)/2) * 1.0
    }

    private fun getNonLinealFuel(value: Int): Double {
        var fuel = 0.0

        crabs.forEach {
//            println("gaus($value, $it) = ${gauss(abs(value-it))}")
            fuel += gauss(abs(value - it))
        }

        return fuel
    }

    fun computeLinealFuel(): Int {
        return getLinealFuel(computeMedian())
    }

    fun computeNonLinealFuel(): Int {
        return min(
            getNonLinealFuel(floor(computeMean()).toInt()),
            getNonLinealFuel(ceil(computeMean()).toInt())
        ).toInt()
    }
}