package day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day8Test {

    private val day8: Day8 = Day8()

    @Test
    fun testCountUniqueNumbers() {
        val expected = 26
        assertEquals(expected, day8.countUniqueNumbers("digitsdataset"))
    }
}