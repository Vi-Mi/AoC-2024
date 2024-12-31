
import java.io.File

fun isXMAS(M: Char, A: Char, S: Char): Int {
    if (M == 'M' && A == 'A' && S == 'S') {
//        print(1)
        return 1
    }
    return 0
}

fun findAll1(text: MutableList<List<Char>>) : Int{
    var count = 0
    for ((i, line) in text.withIndex()) {
        for ((j, char) in line.withIndex()) {
            if (char == 'X') {
//                print("X")
                if (i >=3) {
                    count += isXMAS(text[i-1][j], text[i-2][j], text[i-3][j])
//                    println()
                    if (j >= 3) {
                        count += isXMAS(text[i-1][j-1], text[i-2][j-2], text[i-3][j-3])
                    }
                    if (j < text[i].size - 3) {
                        count += isXMAS(text[i-1][j+1], text[i-2][j+2], text[i-3][j+3])
                    }
                }
                if (i < text.size - 3) {
                    count += isXMAS(text[i+1][j], text[i+2][j], text[i+3][j])
                    if (j >= 3) {
                        count += isXMAS(text[i+1][j-1], text[i+2][j-2], text[i+3][j-3])
                    }
                    if (j < text[i].size - 3) {
                        count += isXMAS(text[i+1][j+1], text[i+2][j+2], text[i+3][j+3])
                    }
                }
                if (j >= 3) {
                    count += isXMAS(text[i][j-1], text[i][j-2], text[i][j-3])
                }
                if (j < text[i].size - 3) {
                    count += isXMAS(text[i][j+1], text[i][j+2], text[i][j+3])
                }
            }
        }
    }
    return count
}

fun isMS(one: Char, two: Char): Boolean {
    if ((one == 'M' && two == 'S')||(one == 'S' && two == 'M')) {
        return true
    }
    return false
}

fun findAll2(text: MutableList<List<Char>>) : Int{
    var count = 0
    for ((i, line) in text.withIndex()) {
        for ((j, char) in line.withIndex()) {
            if (char == 'A' && i > 0 && i < text.size -1 &&
                j > 0 && j < line.size - 1) {
                if (isMS(text[i-1][j-1],text[i+1][j+1]) && isMS(text[i-1][j+1],text[i+1][j-1])) {
                    count += 1
                }
            }
        }
    }
    return count
}


fun main() {
    val f = File("input.txt")
    val text = f.readLines()
    val list = mutableListOf<List<Char>>()
    for (line in text) {
        list.add(line.toCharArray().toList())
    }
//    findALL(list)
//    println(findAll1(list))
    println(findAll2(list))
}