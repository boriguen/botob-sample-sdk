package com.botob.samplesdk.equation.parser

import com.botob.samplesdk.equation.Constant
import com.botob.samplesdk.equation.Equation
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class EquationParserTest {

    @Test
    fun testParse_controlInputSuccessful() {
        // Initialize testing data.
        val lines = listOf(
                "offset = 4 + destination + 1",
                "location = 1 + origin + offset",
                "origin = 3 + 5",
                "destination = 2"
        )

        // Parse equations and get them in ascending order by name.
        val equations = EquationParser().parse(lines)

        // Check equation 1.
        Assert.assertEquals("destination", equations[0].name)
        Assert.assertTrue(equations[0].has(Constant(2)))

        // Check equation 2.
        Assert.assertEquals("location", equations[1].name)
        Assert.assertTrue(equations[1].has(Constant(1)))
        Assert.assertTrue(equations[1].has(Equation("origin")))
        Assert.assertTrue(equations[1].has(Equation("offset")))

        // Check equation 3.
        Assert.assertEquals("offset", equations[2].name)
        Assert.assertTrue(equations[2].has(Constant(4)))
        Assert.assertTrue(equations[2].has(Equation("destination")))
        Assert.assertTrue(equations[2].has(Constant(1)))

        // Check equation 4.
        Assert.assertEquals("origin", equations[3].name)
        Assert.assertTrue(equations[3].has(Constant(3)))
        Assert.assertTrue(equations[3].has(Constant(5)))
    }

    @Test
    fun testParse_controlInputWithAdditionalBlanksSuccessful() {
        // Initialize testing data.
        val lines = listOf(
                "offset   = 4 + destination + 1",
                "location \t = 1 + origin + offset",
                "origin =    3   +     5 ",
                "destination    =   2"
        )

        // Parse equations and get them in ascending order by name.
        val equations = EquationParser().parse(lines)

        // Check equation 1.
        Assert.assertEquals("destination", equations[0].name)
        Assert.assertTrue(equations[0].has(Constant(2)))

        // Check equation 2.
        Assert.assertEquals("location", equations[1].name)
        Assert.assertTrue(equations[1].has(Constant(1)))
        Assert.assertTrue(equations[1].has(Equation("origin")))
        Assert.assertTrue(equations[1].has(Equation("offset")))

        // Check equation 3.
        Assert.assertEquals("offset", equations[2].name)
        Assert.assertTrue(equations[2].has(Constant(4)))
        Assert.assertTrue(equations[2].has(Equation("destination")))
        Assert.assertTrue(equations[2].has(Constant(1)))

        // Check equation 4.
        Assert.assertEquals("origin", equations[3].name)
        Assert.assertTrue(equations[3].has(Constant(3)))
        Assert.assertTrue(equations[3].has(Constant(5)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testParse_illegalEquationNameSuccessful() {
        // Initialize testing data.
        val lines = listOf("offset_ = 4 + destination + 1")

        // Parse equations.
        EquationParser().parse(lines)
    }
}