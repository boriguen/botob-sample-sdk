package com.botob.samplesdk.equation.reader

import org.junit.Assert
import org.junit.Test

class EquationFileTest {

    @Test
    fun testFormatSolution_successful() {
        val expectedSolution = "destination = 2\n" +
                "location = 16\n" +
                "offset = 7\n" +
                "origin = 8"
        val stream = javaClass.classLoader.getResourceAsStream("control_equations.txt")
        Assert.assertEquals(expectedSolution, EquationSolver(stream).formatSolution())
    }
}