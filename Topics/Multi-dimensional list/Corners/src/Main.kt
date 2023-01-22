fun main() {
    // Do not touch code below
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readln().toInt()
    for (i in 0 until n) {
        val strings = readln().split(' ').toMutableList()
        inputList.add(strings)
    }
    // write your code here
    val topLeft = inputList[0][0]
    val topRight = inputList[0][inputList[0].lastIndex]
    val bottomLeft = inputList[n-1][0]
    val bottomRight = inputList[n-1][inputList[n-1].lastIndex]
    print("""
        $topLeft $topRight
        $bottomLeft $bottomRight
    """.trimIndent())
}