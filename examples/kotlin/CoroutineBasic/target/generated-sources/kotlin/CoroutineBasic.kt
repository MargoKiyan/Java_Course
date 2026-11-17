import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch {
        delay(1000)
        sus()
    }
}

suspend fun sus() {
    println("Стоп машина!")
}
