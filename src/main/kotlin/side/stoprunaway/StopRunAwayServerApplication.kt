package side.stoprunaway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StopRunAwayServerApplication

fun main(args: Array<String>) {
    runApplication<StopRunAwayServerApplication>(*args)
}
