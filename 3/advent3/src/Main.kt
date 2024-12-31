import java.io.File

fun getAll(input: String): Int {
    var out = 0
    val regex = Regex("do\\(\\)|don't\\(\\)|mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)")
    val matches = regex.findAll(input)
    var isDo = true
    for (match in matches) {
        if (match.value == "do()") {
            isDo = true
            continue
        }
        if (match.value == "don't()") {
            isDo = false
            continue
        }
        if (isDo) {
            val (a, b) = match.destructured
            println("a: $a, b: $b")
            out += a.toInt() * b.toInt()
        }
    }
    return out

}


fun main() {
    val f = File("input.txt")
    val text = f.readText()

//    println(text)
    println(getAll(text))
}