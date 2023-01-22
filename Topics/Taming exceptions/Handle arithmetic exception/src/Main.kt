fun main() {
    // put your code here
    val first = readln().toInt()
    val second = readln().toInt()
    if (second == 0) {
        println("Division by zero, please fix the second argument!")
        return
    }
    println(first / second)
}