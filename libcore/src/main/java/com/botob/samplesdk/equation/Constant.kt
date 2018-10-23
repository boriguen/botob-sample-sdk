package com.botob.samplesdk.equation

data class Constant(private val _value: Int) : Operand {

    override fun solve(): Int {
        return _value
    }
}