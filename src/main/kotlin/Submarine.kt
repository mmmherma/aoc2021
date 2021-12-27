import bingo.Bingo
import crab.Crab
import evolution.FishPopulation
import healthdiagnosis.Diagnosis
import map.HydrothermalMap
import movement.Movement
import rangefinder.RangeFinder

fun main() {
    val fileUtils: RangeFinder = RangeFinder()
    val movement: Movement = Movement()
    val diagnosis: Diagnosis = Diagnosis()
    val bingo: Bingo = Bingo()
    val map = HydrothermalMap()
    val lanternFishPopulation = FishPopulation()
    val crab = Crab()

    println("AOC2021")
    // DAY 1
    println("DAY 1 (2/2): there are ${fileUtils.computeSlidingWindowMeasures(3, "rangefindermeasures")} measures" +
            "greater than the previous one.")
    // DAY 2
    movement.computeUsingAim("movements")
    println("DAY 2 (2/2): the total move of the submarine is ${movement.getFinalMovementCost()}.")
    // DAY 3
    println("DAY 3 (1/2): the power consumption is ${diagnosis.getDiagnosisPowerConsumption()}.")
    println("DAY 3 (2/2): life support rating of the submarine is ${diagnosis.getLifeSupportRate()}.")
    // DAY 4
    bingo.readProblem("bingodataset")
    println("DAY 4 (1/2): the first board that solves bingo has a value of ${bingo.solveBingo()}")
    println("DAY 4 (2/2): the last board that solves bingo has a value of ${bingo.solveLastBingo()}")
    // DAY 5
    map.readMap("hydrothermalvents")
    println("DAY 5 (2/2): there are ${map.getOverlap()} points where hydrothermal overlaps.")
    // DAY 6
    println("DAY 6 (1/2): lanternfish population size within 80 days ${lanternFishPopulation.evolve(80, "lanternfishpopulation")}.")
    println("DAY 6 (2/2): lanternfish population size within 256 days ${lanternFishPopulation.evolve(256, "lanternfishpopulation")}.")
    // DAY 7
    crab.readProblem("crabs")
    println("DAY 7 (1/2): fuel needed when only horizontal moves performed is ${crab.computeLinealFuel()}.")
    println("DAY 7 (1/2): fuel needed when only horizontal moves performed is ${crab.computeNonLinealFuel()}.")

}