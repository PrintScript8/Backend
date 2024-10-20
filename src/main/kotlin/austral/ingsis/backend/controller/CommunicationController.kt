package austral.ingsis.backend.controller

import austral.ingsis.backend.message.RedisMessageEmitter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommunicationController
    @Autowired
    constructor(private val redisEmitter: RedisMessageEmitter) {
        // val testClient: RestClient = RestClient.builder().baseUrl("http://localhost:8080").build()

        @GetMapping("/testRespondMessage")
        fun respondMessage(): ResponseEntity<String> {
            return ResponseEntity.ok("Greetings from Backend!")
        }

        @GetMapping("/testConnection")
        fun testConnection(): ResponseEntity<String> {
            return ResponseEntity.ok("Backend is online")
        }

        @PostMapping("/redis/test")
        fun testRedis(
            @RequestParam("language") language: String,
            @RequestParam("code") code: String,
            @RequestParam("action") action: String,
            @RequestParam("inputs") inputs: String,
        ): ResponseEntity<String> {
            redisEmitter.publishEvent(language, code, action, inputs)
            return ResponseEntity.ok("Redis is working")
        }
    }
