const val G = 9.8
fun main() {
    // write your code here
    val (liquidDensity, liquidColumnHeight) = List(2) { readln().toDouble() }
    val liquidPressure = liquidDensity * G * liquidColumnHeight
    print(liquidPressure)
}