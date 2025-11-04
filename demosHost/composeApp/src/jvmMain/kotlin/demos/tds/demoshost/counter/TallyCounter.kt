package demos.tds.demoshost.counter

data class TallyCounter(val maxValue: Int, val currentValue: Int = 0) {

    fun increment(): TallyCounter =
        if (currentValue <= maxValue) {
            copy(currentValue = currentValue + 1)
        }
        else
            this

    fun decrement(): TallyCounter =
        if (currentValue > 0) {
            copy(currentValue = currentValue - 1)
        }
        else this
}

val counter = TallyCounter(10)
val anotherCounter = counter.increment()
