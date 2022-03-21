package by.varyvoda.matvey.domain

import java.util.ArrayList
import java.util.function.Consumer

class BringCounter(count: Long = 0) {

    var count = count
        private set

    private val listeners: MutableList<Consumer<Long>> = ArrayList()

    fun setBringListener(listener: Consumer<Long>) {
        listeners.add(listener)
    }

    fun count() {
        count++
        notifyListeners()
    }

    private fun notifyListeners() {
        listeners.forEach { it.accept(count) }
    }
}