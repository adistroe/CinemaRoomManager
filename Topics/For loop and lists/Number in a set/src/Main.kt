const val YES = "YES"
const val NO = "NO"

fun main() {
    // write your code here
    val N = readln().toInt()
    val numbers: MutableList<Int> = MutableList(N) { readln().toInt() }
    val M = readln().toInt()
    print(if (numbers.contains(M)) YES else NO)
}