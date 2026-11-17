// EXECUTION_SCAFFOLD: main wrapper and observation only.
fun main() {
val compose = { a: Int, b: Int -> a * 2 + b }

val result = compose(5, 10)  // Результат: 20
check(result == 20)
println(result)
}
