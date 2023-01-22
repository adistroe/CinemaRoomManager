const val YES = "YES" // if the numbers never occur next to each other
const val NO = "NO" // if the numbers are next to each other

fun main() {
    // write your code here
    val listSize = readln().toInt()
    val numbers = MutableList(listSize) { readln().toInt() }
    val (p, m) = readln().split(' ').map { it.toInt() }

    if (numbers.size < 2) {
        print(YES)
        return
    }

    var found = false

    for (index in 0 until numbers.lastIndex) {
        if (!found) {
            if (numbers[index] == p) {
                if (numbers[index + 1] == m) found = true
            } else {
                if (numbers[index] == m)
                    if (numbers[index + 1] == p) found = true
            }
        }
    }
    print(if (!found) YES else NO)
}