const val JAVA = 1
const val KOTLIN = 2
const val SCALA = 3
const val PYTHON = 4

const val MSG_YES = "Yes!"
const val MSG_NO = "No!"
const val MSG_UNKNOWN = "Unknown number"

fun main() {
    // write your code here
    val input = readln().toInt()
    print(
        when (input) {
            KOTLIN -> MSG_YES
            in arrayOf(JAVA, SCALA, PYTHON) -> MSG_NO
            else -> MSG_UNKNOWN
        }
    )
}