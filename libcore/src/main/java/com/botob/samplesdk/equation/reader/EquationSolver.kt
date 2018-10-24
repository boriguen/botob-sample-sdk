package com.botob.samplesdk.equation.reader

import com.botob.samplesdk.equation.Equation
import com.botob.samplesdk.equation.parser.EquationParser
import java.io.InputStream

/**
 * EquationSolver is the class allowing to solve equations from a given input stream.
 */
class EquationSolver(private val _stream: InputStream) {
    /**
     * Formats the solution as text after solving it.
     *
     * @return the resulting formatted string.
     */
    fun formatSolution(): String {
        val equations = extractEquations()
        val iterator = equations.iterator()
        val stringBuilder = StringBuilder(iterator.next().toString())
        while (iterator.hasNext()) {
            stringBuilder.append('\n').append(iterator.next().toString())
        }
        return stringBuilder.toString()
    }

    /**
     * Extracts the list of Equation objects from the passed InputStream object.
     *
     * @return the resulting list of Equation objects.
     */
    private fun extractEquations(): List<Equation> {
        return EquationParser().parse(_stream.bufferedReader().readLines())
    }
}