
import java.io.File

fun goesOut1(dir: String, i: Int, j: Int, size: Int) : Boolean {
    if ((dir == "UP" && i <= 0) ||
        (dir == "DOWN" && i >= size - 1) ||
        (dir == "LEFT" && j <= 0) ||
        (dir == "RIGHT" && j >= size - 1)) {
        return true
    }
    return false
}

fun goesOut2(dir: String, i: Int, j: Int, sizeX: Int, sizeY: Int) : Boolean {
    if ((dir == "UP" && i <= 0) ||
        (dir == "DOWN" && i >= sizeY - 1) ||
        (dir == "LEFT" && j <= 0) ||
        (dir == "RIGHT" && j >= sizeX - 1)) {
        return true
    }
    return false
}

fun findLengthOfWay(text: MutableList<MutableList<Char>>) : Int {
    print(text)
    var guars0 = 0
    var guars1 = 0
    var direction = "UP"
    for ((i, line) in text.withIndex()) {
        for ((j, char) in line.withIndex()) {
            if (char == '^') {
                guars0 = i
                guars1 = j
            }
        }
    }
    var t = text

    var count = 0
    while (!goesOut1(direction, guars0, guars1, text.size)) {
        if (direction == "UP") {
//            println("UP")
            val beforeGuard = text[guars0 - 1][guars1]
            if (beforeGuard == '.') {
                guars0 -= 1
                t[guars0][guars1] = '^'
                count += 1
            } else if (beforeGuard == '^') {
                guars0 -= 1
            } else if (beforeGuard == '#') {
                direction = "RIGHT"
            }
        } else if (direction == "RIGHT") {
//            println("RIGHT")
            val beforeGuard = text[guars0][guars1 + 1]
            if (beforeGuard == '.') {
                guars1 += 1
                t[guars0][guars1] = '^'
                count += 1
            } else if (beforeGuard == '^') {
                guars1 += 1
            } else if (beforeGuard == '#') {
                direction = "DOWN"
            }
        } else if (direction == "DOWN") {
//            println("DOWN")
            val beforeGuard = text[guars0 + 1][guars1]
            if (beforeGuard == '.') {
                guars0 += 1
                t[guars0][guars1] = '^'
                count += 1
            } else if (beforeGuard == '^') {
                guars0 += 1
            } else if (beforeGuard == '#') {
                direction = "LEFT"
            }
        } else if (direction == "LEFT") {
//            println("LEFT")
            val beforeGuard = text[guars0][guars1 - 1]
            if (beforeGuard == '.') {
                guars1 -= 1
                t[guars0][guars1] = '^'
                count += 1
            } else if (beforeGuard == '^') {
                guars1 -= 1
            } else if (beforeGuard == '#') {
                direction = "UP"
            }
        }

    }
    return count + 1
}


fun isCycleWay(t: MutableList<MutableList<Char>>) : Boolean {
    val text = t
    var guars0 = 0  //y
    var guars1 = 0  //x
    var direction = "UP"
    for ((i, line) in text.withIndex()) {
        for ((j, char) in line.withIndex()) {
            if (char == '^') {
                guars0 = i
                guars1 = j
            }
        }
    }
//    var t = text

//    var count = 0
    while (!goesOut2(direction, guars0, guars1, text.size, text[0].size)) {
        if (direction == "UP") {
//            println("U")
            val beforeGuard = t[guars0 - 1][guars1]
            if (beforeGuard == '.' || beforeGuard == 'v' || beforeGuard == '<' || beforeGuard == '>') {
                guars0 -= 1
                t[guars0][guars1] = '^'
//                count += 1
            } else if (beforeGuard == '^') {
                guars0 -= 1
//                println("UP")
                return true
            } else if (beforeGuard == '#') {
                direction = "RIGHT"
//                println("!!!!!!!!!")
            }
        } else if (direction == "RIGHT") {
//            println("R")
            val beforeGuard = t[guars0][guars1 + 1]
            if (beforeGuard == '.' || beforeGuard == '^' || beforeGuard == 'v' || beforeGuard == '<') {
                guars1 += 1
                t[guars0][guars1] = '>'
//                count += 1
            } else if (beforeGuard == '>') {
                guars1 += 1
//                println("RIGHT")
                return true
            } else if (beforeGuard == '#') {
                direction = "DOWN"
            }
        } else if (direction == "DOWN") {
//            println("D")
            val beforeGuard = t[guars0 + 1][guars1]
            if (beforeGuard == '.' || beforeGuard == '^' || beforeGuard == '<' || beforeGuard == '>') {
                guars0 += 1
                t[guars0][guars1] = 'v'
//                count += 1
            } else if (beforeGuard == 'v') {
                guars0 += 1
//                println("DOWN")
                return true
            } else if (beforeGuard == '#') {
                direction = "LEFT"
            }
        } else if (direction == "LEFT") {
//            println("L")
            val beforeGuard = t[guars0][guars1 - 1]
            if (beforeGuard == '.' || beforeGuard == 'v' || beforeGuard == '^' || beforeGuard == '>') {
                guars1 -= 1
                t[guars0][guars1] = '<'
//                count += 1
            } else if (beforeGuard == '<') {
                guars1 -= 1
//                println("LEFT")
                return true
            } else if (beforeGuard == '#') {
                direction = "UP"
            }
        }
    }
    println("OUT")
    return false
}


fun main() {
    val f = File("input.txt")
    val text = f.readLines()
    val list = mutableListOf<MutableList<Char>>()
    for (line in text) {
        var l = mutableListOf<Char>()
        for (char in line) {
            l.add(char)
        }
        list.add(l)
    }

//    println(findLengthOfWay(list))  //1

    var count = 0
    for (i in 0..list.size - 1) {
        for (j in 0..list[0].size - 1) {
            var list2 = MutableList(list.size) { MutableList(list[0].size) { ' ' } }
            for (i in 0..list.size - 1) {
                for (j in 0..list[0].size - 1) {
                    list2[i][j] = list[i][j]
                }
            }
            if (list2[i][j] == '.') {
                list2[i][j] = '#'
                if (isCycleWay(list2)) {
                    count += 1
                }
            }
//            if (j == 1) {
//                for (line in list2) {
//                    println(line)
//                }
//                println(count)
//                return
//            }
        }
    }
    println(count)

}