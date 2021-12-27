package evolution

import utils.FileUtils

class FishPopulation {
    private val fileUtils = FileUtils()
    private var individuals = mutableMapOf<Int, Long>(
        0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0, 5 to 0, 6 to 0, 7 to 0, 8 to 0,
    )

    private fun readProblem(fileName: String) {
        // Read file within initial lantern fishes
        val data = fileUtils.getFileFromResources(fileName)

        individuals = mutableMapOf<Int, Long>(
            0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0, 5 to 0, 6 to 0, 7 to 0, 8 to 0,
        )

        val tokens = data[0].split(",")

        tokens.forEach {
            individuals[it.toInt()] = individuals[it.toInt()]!!.inc()
        }
    }

    fun evolve(numberDays: Int, fileName: String): Long {
        readProblem(fileName)
        for (i in 0 until numberDays) {
            val forkingFishes = individuals[0]

            for (j in 0 until 8) {
                individuals[j+1]?.let { individuals.put(j, it) }
            }
            individuals[8] = forkingFishes!!
            individuals[6] = individuals[6]?.plus(forkingFishes)!!
        }

        // Compute total amount of individuals
        var sum: Long = 0
        individuals.forEach{
            sum += it.value
        }

        return sum
    }
}