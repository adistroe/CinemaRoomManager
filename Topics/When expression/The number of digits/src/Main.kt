import javax.print.attribute.standard.MediaSize.ISO

const val ONE = 1
const val TWO = 2
const val THREE = 3
const val FOUR = 4

fun main() {
    // put your code here
    val num = readln()
    when (num.length) {
        ONE -> print(ONE)
        TWO -> print(TWO)
        THREE -> print(THREE)
        FOUR -> print(FOUR)
    }
}