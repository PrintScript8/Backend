package austral.ingsis.backend

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    val dotenv = Dotenv.load()

    // Load environment variables into the system properties
    // This is necessary so SpringBoot and SpringSecurity can access them
    System.setProperty("AUTH0_AUDIENCE", dotenv["AUTH0_AUDIENCE"])
    System.setProperty("AUTH_SERVER_URI", dotenv["AUTH_SERVER_URI"])
    System.setProperty("AUTH_CLIENT_ID", dotenv["AUTH_CLIENT_ID"])
    System.setProperty("AUTH_CLIENT_SECRET", dotenv["AUTH_CLIENT_SECRET"])
    System.setProperty("AUTH0_USER_EMAIL", dotenv["AUTH0_USER_EMAIL"])
    System.setProperty("AUTH0_USER_PASSWORD", dotenv["AUTH0_USER_PASSWORD"])

    runApplication<BackendApplication>(*args)
}
