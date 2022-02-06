package by.varyvoda.matvey.domain

import java.util.ArrayList
import java.util.function.Consumer

class BringCounter(count: Int = 0) {

    var count = count
        private set

    private val listeners: MutableList<Consumer<Int>> = ArrayList()

    fun setBringListener(listener: Consumer<Int>) {
        listeners.add(listener)
    }

    fun count() {
        count++
        notifyListeners();
    }

    private fun notifyListeners() {
        listeners.forEach { it.accept(count) }
    }
}