package healthdiagnosis

import utils.FileUtils

class Diagnosis {
    private val fileUtils: FileUtils = FileUtils()
    private val diagnosisReport = fileUtils.getFileFromResources("diagnosisreport")

    // Part 1
    private var gammaRate = ""
    private var epsilonRate = ""
    private var powerConsumption = 0 // gammaRate * epsilonRate

    // Part 2
    private var oxygenGeneratorRate = ""
    private var co2ScrubberRate = ""
    private var lifeSupportRate = 0

    private fun computeGammaRate() {
        gammaRate = ""

        for (i in 0 until diagnosisReport[0].length) {
            gammaRate += getMostCommonBit(i)
        }
    }

    private fun computeEpsilonRate() {
        epsilonRate = ""

        if (gammaRate == "") {
            computeGammaRate()
        }

        epsilonRate = flipBitString(gammaRate)
    }

    fun getDiagnosisPowerConsumption(): Int {
        computeGammaRate()
        computeEpsilonRate()

        powerConsumption = gammaRate.toInt(2) * epsilonRate.toInt(2)

        return powerConsumption
    }

    private fun flipBitString(str: String): String {
        var flippedString = ""

        for (i in str.indices) {
            if (str[i] == '0') {
                flippedString += '1'
            } else if (str[i] == '1') {
                flippedString += '0'
            }
        }

        return flippedString
    }

    private fun getMostCommonBit(position: Int): Char {
        var n0 = 0
        var n1 = 0

        diagnosisReport.forEach {
            if (it[position] == '0') {
                n0++
            } else if (it[position] == '1') {
                n1++
            }
        }

        return if (n0 > n1) '0' else '1'
    }

    private fun filterDiagnosisReport(report: List<String>, position: Int, direction: Int): String {
        if (report.size == 1) {
            return report[0]
        } else if (report.size == 2 && getMostCommonBit(position) == '1') {
            if (direction == 1) {
                return if (report[0][position] == '1') report [0] else report[1]
            } else {
                return if (report[0][position] == '0') report [0] else report[1]
            }
        } else {
            var reportWithin0: ArrayList<String> = ArrayList()
            var reportWithin1: ArrayList<String> = ArrayList()
            report.forEach {
                if (it[position] == '0') {
                    reportWithin0.add(it)
                } else {
                    reportWithin1?.add(it)
                }
            }

            return if (direction == 1) {
                if (reportWithin0.toList().size > reportWithin1.toList().size)
                    filterDiagnosisReport(reportWithin0.toList(), position+1, 1)
                else
                    filterDiagnosisReport(reportWithin1.toList(), position+1, 1)
            } else {
                if (reportWithin0.toList().size > reportWithin1.toList().size)
                    filterDiagnosisReport(reportWithin1.toList(), position+1, 0)
                else
                    filterDiagnosisReport(reportWithin0.toList(), position+1, 0)
            }
        }
    }

    private fun computeOxygenGeneratorRate() {
        oxygenGeneratorRate = filterDiagnosisReport(diagnosisReport, 0, 1)
    }

    private fun computeCO2ScrubberRate() {
        co2ScrubberRate = filterDiagnosisReport(diagnosisReport, 0, 0)
    }

    fun getLifeSupportRate(): Int {
        computeOxygenGeneratorRate()
        computeCO2ScrubberRate()

        lifeSupportRate = oxygenGeneratorRate.toInt(2) * co2ScrubberRate.toInt(2)

        return lifeSupportRate
    }
}