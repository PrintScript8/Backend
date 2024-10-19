package austral.ingsis.backend.controller

import austral.ingsis.backend.message.BackendRequestEmitter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommunicationController {
    // val testClient: RestClient = RestClient.builder().baseUrl("http://localhost:8080").build()
    val redisEmitter: BackendRequestEmitter = BackendRequestEmitter("backend", RedisTemplate())

    @GetMapping("/testRespondMessage")
    fun respondMessage(): ResponseEntity<String> {
        return ResponseEntity.ok("Greetings from Backend!")
    }

    @GetMapping("/testConnection")
    fun testConnection(): ResponseEntity<String> {
        return ResponseEntity.ok("Backend is online")
    }

    @PostMapping("/redis/test")
    fun testRedis(): ResponseEntity<String> {
        redisEmitter.emitRequest("test", "test", "test", "test")
        return ResponseEntity.ok("Redis is working")
    }
}
