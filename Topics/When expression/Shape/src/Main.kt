const val SQUARE = 1
const val CIRCLE = 2
const val TRIANGLE = 3
const val RHOMBUS = 4

const val MSG_SQUARE = "square"
const val MSG_CIRCLE = "circle"
const val MSG_TRIANGLE = "triangle"
const val MSG_RHOMBUS = "rhombus"

const val MSG_NOT_FOUND = "There is no such shape!"
const val MSG_SHAPE_CHOSEN = "You have chosen a "


fun main(args: Array<String>) {
    // write your code here
    val input = readln().toInt()
    val validInput = listOf(SQUARE, CIRCLE, TRIANGLE, RHOMBUS)
    if (input in validInput)
        print(
            MSG_SHAPE_CHOSEN +
                    when (input) {
                        SQUARE -> MSG_SQUARE
                        CIRCLE -> MSG_CIRCLE
                        TRIANGLE -> MSG_TRIANGLE
                        RHOMBUS -> MSG_RHOMBUS
                        else -> MSG_NOT_FOUND
                    }
        )
    else print(MSG_NOT_FOUND)
}