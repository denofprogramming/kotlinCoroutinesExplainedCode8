import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {


    logMessage("Hello")
    //withContext(Dispatchers.Default) {

    coroutineScope {

        val jobs: List<Job> = (1..5).map {
            launch {
                logMessage("Started Coroutine $it")
                delay(100)
                logMessage("Ended Coroutine $it")
            }
        }
        logMessage("Created all Coroutines")
        jobs.forEach { it.start() }

    }

    logMessage("Finished all Coroutines")
    // }
    logMessage("world")


}


fun logMessage(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage("id: $id ${it.key} = ${it}") }
}