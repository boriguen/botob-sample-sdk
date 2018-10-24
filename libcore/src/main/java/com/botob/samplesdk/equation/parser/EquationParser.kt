package com.botob.samplesdk.equation.parser

import com.botob.samplesdk.equation.Constant
import com.botob.samplesdk.equation.Equation
import com.botob.samplesdk.equation.Operand
import java.security.InvalidParameterException

/**
 * EquationParser is the class allowing to transform a list of text lines into Equation objects.
 */
class EquationParser {

    companion object {
        /**
         * The regex pattern used to detect an operand.
         */
        const val OPERAND_PATTERN = "([A-Za-z0-9]+)"

        /**
         * The regex pattern used to detect an equation name.
         */
        const val EQUATION_NAME_PATTERN = "$OPERAND_PATTERN[\\s]*="
    }

    /**
     * The list of equations by name.
     */
    private val _equationsByName = HashMap<String, Equation>()

    /**
     * Parses the given list of lines into Equation objects.
     *
     * @param lines the lines to parse
     * @return a list of Equation objects in ascending order by name.
     */
    fun parse(lines: List<String>): List<Equation> {
        _equationsByName.clear()
        for (line in lines) {
            parse(line)
        }
        return _equationsByName.values.toList().sortedBy { it.name }
    }

    /**
     * Parses the given line into an Equation object.
     *
     * @param line the line to parse and store into the Equation map.
     */
    private fun parse(line: String) {
        // Parse equation name and instantiate LHS equation.
        val equation = getEquation(parseName(line))

        // Parse equation operands.
        val regex = Regex(OPERAND_PATTERN)
        val parts = regex.findAll(line.substringAfter('='))
        for (part in parts.map { it.value }) {
            equation.add(parseOperand(part))
        }
    }

    /**
     * Gets the equation of given name from the map if already existing, otherwise, creates a new one
     * and pushes it to the map.
     *
     * @param name the name of equation.
     * @return the resulting Equation object.
     */
    private fun getEquation(name: String): Equation {
        val equation: Equation
        if (!_equationsByName.containsKey(name)) {
            equation = Equation(name)
            _equationsByName[name] = equation
        } else {
            equation = _equationsByName[name]!!
        }
        return equation
    }

    /**
     * Parses the equation name from the given line.
     *
     * @param line the line to parse.
     * @return the resulting name.
     * @throws InvalidParameterException when the variable does not match naming conventions.
     */
    private fun parseName(line: String): String {
        val parts = Regex(EQUATION_NAME_PATTERN).findAll(line)
        if (parts.none()) {
            throw IllegalArgumentException("The variable name in '$line' does not match naming conventions, i.e. Character.isLetterOrDigit(c) == true")
        } else {
            return parts.first().groupValues[1]
        }
    }

    /**
     * Parses an operand string into an Operand object.
     *
     * @param operandString the operand string to parse.
     * @return the resulting Operand object.
     */
    private fun parseOperand(operandString: String): Operand {
        val operand: Operand
        val intValue = operandString.toIntOrNull()
        operand = if (intValue != null) {
            Constant(intValue)
        } else {
            getEquation(operandString)
        }
        return operand
    }
}