package com.botob.samplesdk.equation

/**
 * Constant is the data class used to represent constants.
 */
data class Constant(private val _value: Int) : Operand {
    /**
     * Solves the constant.
     *
     * @return the resulting value.
     */
    override fun solve(): Int {
        return _value
    }
}