import kotlin.math.pow

const val PI = 3.1415

fun main() {
    // write your code here
    val radius = readln().toDouble()
    val circleArea = PI * radius.pow(2)
    println(circleArea)
}