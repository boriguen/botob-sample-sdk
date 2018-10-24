package com.botob.samplesdk.equation

/**
 * Equation is the data class used to represent equations.
 */
data class Equation(private val _name: String) : Operand {
    /**
     * The name getter.
     */
    val name get() = _name

    /**
     * The value of this.
     */
    private var _value: Int = Int.MIN_VALUE

    /**
     * The list of operands composing this.
     */
    private var _operands: MutableList<Operand> = ArrayList()

    /**
     * Solves the equation via recursion.
     *
     * @return the resulting value.
     */
    override fun solve(): Int {
        if (_value == Int.MIN_VALUE) {
            _value = 0
            for (operand in _operands) {
                _value += operand.solve()
            }
            //_value = _operands.sumBy { it.solve() }
        }
        return _value
    }

    /**
     * Gets the string representation of this as "name = value".
     *
     * @return the resulting string.
     */
    override fun toString(): String {
        return "$name = ${solve()}"
    }

    /**
     * Adds the given operand.
     *
     * @param operand one of the operands to be used to solve the equation.
     */
    fun add(operand: Operand) {
        _operands.add(operand)
    }

    /**
     * Checks whether the given operand is already added.
     *
     * @param operand the operand for which to check if it is already added.
     * @return true if the operand is already added; otherwise, false.
     */
    internal fun has(operand: Operand): Boolean {
        return _operands.contains(operand)
    }
}