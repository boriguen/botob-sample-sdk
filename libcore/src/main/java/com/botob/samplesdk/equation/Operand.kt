package com.botob.samplesdk.equation

/**
 * Operand is the interface to be implemented by any equation part on the RHS.
 */
interface Operand {
    /**
     * Solves the operand.
     *
     * @return the resulting value.
     */
    fun solve(): Int
}