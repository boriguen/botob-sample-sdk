package com.botob.samplesdk.equation.parser

import com.botob.samplesdk.equation.Constant
import com.botob.samplesdk.equation.Equation
import com.botob.samplesdk.equation.Operand
import java.security.InvalidParameterException

class EquationParser {

    companion object {
        const val OPERAND_PATTERN = "([\\w]+)"
        const val EQUATION_NAME_PATTERN = "$OPERAND_PATTERN[\\s]*="
    }

    private val _equationsByName = HashMap<String, Equation>()

    fun parse(lines: List<String>): List<Equation> {
        _equationsByName.clear()
        for (line in lines) {
            parse(line)
        }
        return _equationsByName.values.toList().sortedBy { it.name }
    }

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

    private fun parseName(line: String): String {
        val parts = Regex(EQUATION_NAME_PATTERN).findAll(line)
        if (parts.none()) {
            throw InvalidParameterException("The variable name in '$line' does not match naming conventions, i.e. Character.isLetterOrDigit(c) == true")
        } else {
            return parts.first().groupValues[1]
        }
    }

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