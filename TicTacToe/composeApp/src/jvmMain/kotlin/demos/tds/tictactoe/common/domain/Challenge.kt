package demos.tds.tictactoe.common.domain

/**
 * Data type that characterizes challenges.
 * @property challenger     The challenger
 * @property challenged     The challenged player
 */
data class Challenge(val challenger: User, val challenged: User)


/**
 * The first player to move for this challenge.
 */
val Challenge.firstToMove: User
    get() = challenger