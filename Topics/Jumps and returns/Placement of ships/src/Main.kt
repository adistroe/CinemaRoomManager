const val MAX_ROWS = 5
const val MAX_COLUMNS = 5

fun main() {
    // put your code here
    val (x1, y1) = readln().split(' ').map { it.toInt() }
    val (x2, y2) = readln().split(' ').map { it.toInt() }
    val (x3, y3) = readln().split(' ').map { it.toInt() }

    val occupiedRows = listOf(x1, x2, x3)
    val occupiedColumns = listOf(y1, y2, y3)

    val freeRows = (1..MAX_ROWS).subtract(occupiedRows.toSet())
    val freeColumns = (1..MAX_COLUMNS).subtract(occupiedColumns.toSet())

    println(freeRows.joinToString(" "))
    println(freeColumns.joinToString(" "))
}