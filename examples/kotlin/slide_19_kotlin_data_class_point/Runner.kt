// EXECUTION_SCAFFOLD: main wrapper and observation only.
fun main() {
data class Point(val x: Int, val y: Int)

val point = Point(10, 20)
val newPoint = point.copy(x = 100)
println(newPoint)  // Виведе: Point(x=100, y=20)
check(point == Point(10, 20))
check(newPoint == Point(100, 20))
}
