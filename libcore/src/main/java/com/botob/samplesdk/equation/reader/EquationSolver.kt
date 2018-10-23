package com.botob.samplesdk.equation.reader

import com.botob.samplesdk.equation.Equation
import com.botob.samplesdk.equation.parser.EquationParser
import java.io.InputStream

class EquationSolver(private val _stream: InputStream) {

    fun formatSolution(): String {
        val equations = extractEquations()
        val iterator = equations.iterator()
        val stringBuilder = StringBuilder(iterator.next().toString())
        while (iterator.hasNext()) {
            stringBuilder.append('\n').append(iterator.next().toString())
        }
        return stringBuilder.toString()
    }

    private fun extractEquations(): List<Equation> {
        return EquationParser().parse(_stream.bufferedReader().readLines())
    }
}