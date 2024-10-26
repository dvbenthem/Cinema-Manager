fun main() {
    // write your code here
    val sizeList = mutableListOf<Int>()

    val readInput = readln().toInt()

    for (i in 0 until readInput) {
        sizeList.add(readln().toInt())
    }

    val readNumbers = readln().split(" ").map { number -> number.toInt() }


        if (sizeList.contains(readNumbers[0]) && sizeList.contains(readNumbers[1])) {
            println("YES")
        } else println("NO")

}

