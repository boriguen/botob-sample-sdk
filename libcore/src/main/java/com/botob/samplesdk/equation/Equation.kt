package com.botob.samplesdk.equation

data class Equation(private val _name: String) : Operand {
    val name get() = _name

    private var _value: Int = Int.MIN_VALUE
    private var _operands: MutableList<Operand> = ArrayList()

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

    override fun toString(): String {
        return "$name = ${solve()}"
    }

    fun add(operand: Operand) {
        _operands.add(operand)
    }

    fun has(operand: Operand): Boolean {
        return _operands.contains(operand)
    }
}