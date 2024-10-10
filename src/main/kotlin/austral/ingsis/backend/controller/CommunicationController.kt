package austral.ingsis.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommunicationController {
    // val testClient: RestClient = RestClient.builder().baseUrl("http://localhost:8080").build()

    @GetMapping("/testRespondMessage")
    fun respondMessage(): ResponseEntity<String> {
        return ResponseEntity.ok("Greetings from Backend!")
    }

    @GetMapping("/testConnection")
    fun testConnection(): ResponseEntity<String> {
        return ResponseEntity.ok("Backend is online")
    }
}
