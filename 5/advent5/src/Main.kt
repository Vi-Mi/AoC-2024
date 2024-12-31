import java.io.File

fun countAll(conditions: MutableList<List<Int>>, pages: MutableList<List<Int>>) : Int {
    var count = 0
    for (page in pages) {
        var isRight = true
        for (cond in conditions) {
            if (cond[0] in page && cond[1] in page && page.indexOf(cond[1]) < page.indexOf(cond[0])) {
                isRight = false
            }
        }

        if (isRight) {
            var middle = page[((page.size - 1) / 2)]
            count += middle
        }
    }
    return count
}

fun firstAndSecond(usingCond: MutableList<List<Int>>, page: MutableList<Int>) : Pair<MutableSet<Int>,MutableSet<Int>> {
//    var first = mutableMapOf<Int,Int>()
//    var second = mutableMapOf<Int,Int>()
    var f = mutableSetOf<Int>()
    var s = mutableSetOf<Int>()
    for ((c1,c2) in usingCond) {
        if (c1 in page && c2 in page) {
            f.add(c1)
            s.add(c2)
//            if (!(c1 in first)) {
//                first[c1] = 0
//            }
//            if (!(c2 in second)) {
//                second[c2] = 0
//            }
//            first[c1] = first[c1]!! + 1
//            second[c2] = second[c2]!! + 1
        }
    }
    return Pair(f,s)
}

fun isOnlyFirst(par: Pair<MutableSet<Int>,MutableSet<Int>>) : MutableSet<Int> {
    var (first,second) = par
    var out = mutableSetOf<Int>()
    for (f in first) {
        if (!(f in second)) {
            out.add(f)
        }
    }
    return out
}

fun useConditions(conditions: MutableList<List<Int>>, page: MutableList<Int>): MutableList<List<Int>> {
    var usingCond = mutableListOf<List<Int>>()
    for ((c1,c2) in conditions) {
        if (c1 in page && c2 in page) {
            usingCond.add(listOf(c1,c2))
        }
    }
    return usingCond
}

fun getRightToCondition(conditions: MutableList<List<Int>>, page: MutableList<Int>) : MutableList<Int> {
    var out = mutableListOf<Int>()

    var usingCond = useConditions(conditions,page)
    var (first,second) = firstAndSecond(usingCond,page)
    var onlyFirst = isOnlyFirst(Pair(first,second))
    while (usingCond.size > 0) {
        for (f in onlyFirst) {
            if (f !in out) {
                out.add(f)
                usingCond = usingCond.filter { it[0] != f }.toMutableList()
            }
        }
//        var newConditions = mutableListOf<List<Int>>()
//        for ((c1,c2) in usingCond) {
//            if (c1 !in out && c2 !in out) {
//                newConditions.add(listOf(c1,c2))
//            }
//        }
//        usingCond = useConditions(newConditions,page)
        var par = firstAndSecond(usingCond,page)
        first = par.first
        second = par.second
        onlyFirst = isOnlyFirst(Pair(first,second))
    }
    for (all in page) {
        if (all !in out) {
            println("" +all + " not in " + out)
//            out.add(all)
//            for (c in conditions) {
//                if (c[0] in out && c[1] in out && out.indexOf(c[1]) < out.indexOf(c[0])) {
//                    println(out)
//                }
//            }
        }
    }
    return out
}

fun countAll2(conditions: MutableList<List<Int>>, pages: MutableList<List<Int>>) : Int {
    var count = 0
    for (page in pages) {
        var isRight = true
        var p = page.toMutableList()
        for (cond in conditions) {
            if (cond[0] in page && cond[1] in page && p.indexOf(cond[1]) < p.indexOf(cond[0])) {
                isRight = false
            }
        }
        if (!isRight) {
            var p2 = getRightToCondition(conditions,p).toMutableList()
            var middle = p2[((p2.size - 1) / 2)+1]
            count += middle
        }
    }
//    println()
    return count
}

fun main() {
    val f = File("input.txt")
    val text = f.readLines()
    val conditions = mutableListOf<List<Int>>()
    val pages = mutableListOf<List<Int>>()
    for (line in text) {
        if ("|" in line) {
            val l = line.split("|")
            conditions.add(listOf(l[0].toInt(), l[1].toInt()))
        } else if (line.length > 0) {
            val l = line.split(",")
            pages.add(l.map { it.toInt() })
//            println(l.map { it[0] })
        }
    }
//    println(conditions)
//    println(pages)
    println(countAll2(conditions, pages))


}